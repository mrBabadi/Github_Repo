package com.bbd.github_repo.domain.usecase

import com.bbd.github_repo.data.ResponseState
import com.bbd.github_repo.domain.entity.UserDetailEntity
import io.reactivex.Flowable

interface GetUserDetailsUseCase {
    operator fun invoke(userName: String): Flowable<ResponseState<UserDetailEntity>>
}

class GetUserDetailsUseCaseImpl : GetUserDetailsUseCase{
    override fun invoke(userName: String): Flowable<ResponseState<UserDetailEntity>> {
        TODO("Not yet implemented")
    }

}