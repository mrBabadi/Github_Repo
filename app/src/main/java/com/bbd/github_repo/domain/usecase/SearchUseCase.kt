package com.bbd.github_repo.domain.usecase

import com.bbd.github_repo.data.ResponseState
import com.bbd.github_repo.domain.entity.UserSearchEntity
import io.reactivex.Flowable

interface SearchUserUseCase {
    operator fun invoke(input:String) : Flowable<ResponseState<UserSearchEntity>>
}

class SearchUserUseCaseImpl : SearchUserUseCase {
    override fun invoke(input: String): Flowable<ResponseState<UserSearchEntity>> {
        TODO("Not yet implemented")
    }

}
