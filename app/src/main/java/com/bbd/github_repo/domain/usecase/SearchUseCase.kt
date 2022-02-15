package com.bbd.github_repo.domain.usecase

import com.bbd.github_repo.data.LocalException
import com.bbd.github_repo.data.ResponseState
import com.bbd.github_repo.data.repository.ApiRepository
import com.bbd.github_repo.domain.entity.UserSearchEntity
import com.bbd.github_repo.domain.ext.toUserSearchEntity
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface SearchUserUseCase {
    operator fun invoke(input:String) : Flowable<ResponseState<UserSearchEntity>>
}

class SearchUserUseCaseImpl @Inject constructor(private val apiRepository: ApiRepository) : SearchUserUseCase {

    override fun invoke(input: String): Flowable<ResponseState<UserSearchEntity>> {
        return apiRepository.searchUser(input).toFlowable()
            .map {response -> ResponseState.success(response.toUserSearchEntity()) }
            .startWith(ResponseState.loading())
            .onErrorReturn { exception ->
                ResponseState.error(LocalException( throwable = exception))
            }
            .subscribeOn(Schedulers.io())
    }

}
