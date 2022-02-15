package com.bbd.github_repo.domain.usecase

import com.bbd.github_repo.data.LocalException
import com.bbd.github_repo.data.ResponseState
import com.bbd.github_repo.data.repository.ApiRepository
import com.bbd.github_repo.domain.entity.UserDetailEntity
import com.bbd.github_repo.domain.ext.toUserDetailsEntity
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface GetUserDetailsUseCase {
    operator fun invoke(userName: String): Flowable<ResponseState<UserDetailEntity>>
}

class GetUserDetailsUseCaseImpl @Inject constructor(private val apiRepository: ApiRepository) :
    GetUserDetailsUseCase {

    override fun invoke(userName: String): Flowable<ResponseState<UserDetailEntity>> {
        return apiRepository.getUserDetails(userName).toFlowable()
            .map { data -> ResponseState.success(data.toUserDetailsEntity()) }
            .startWith(ResponseState.loading())
            .onErrorReturn { exception ->
                ResponseState.error(LocalException(exception))
            }
            .subscribeOn(Schedulers.io())
    }

}