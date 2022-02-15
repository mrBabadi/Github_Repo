package com.bbd.github_repo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.bbd.github_repo.data.ResponseState
import com.bbd.github_repo.data.source.enums.SearchInputAction
import com.bbd.github_repo.domain.entity.UserSearchEntity
import com.bbd.github_repo.domain.usecase.SearchUserUseCase
import com.bbd.github_repo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchUsersViewModel @Inject constructor(private val searchUsersUseCase: SearchUserUseCase) :
    BaseViewModel() {

    private val TAG = SearchUsersViewModel::class.java.name

    private val subject = PublishSubject.create<String>()
    private var lastAction = SearchInputAction.VOICE

    fun search(input: String) {
        subject.onNext(input)
    }

    fun observeSearchResult(): LiveData<ResponseState<UserSearchEntity>> {
        Log.d(TAG, "observeSearchResult: ")
        val switchMap = subject
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .switchMap { t -> searchUsersUseCase(t).toObservable() }
            .observeOn(AndroidSchedulers.mainThread())
        return LiveDataReactiveStreams.fromPublisher(switchMap.toFlowable(BackpressureStrategy.LATEST))
    }

    fun observeInputChanges(): LiveData<SearchInputAction> {
        return LiveDataReactiveStreams.fromPublisher(subject.map {
            lastAction = if (it.isEmpty()) SearchInputAction.VOICE else SearchInputAction.CLEAR
            lastAction

        }.toFlowable(BackpressureStrategy.LATEST))
    }

    fun getLastAction() = lastAction
}