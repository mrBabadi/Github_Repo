package com.bbd.github_repo.domain.usecase

import com.bbd.github_repo.data.source.local.SharedPrefHelper
import javax.inject.Inject

interface SaveLastSearchUseCase {
    operator fun invoke(input: String): Boolean
}

class SaveLastSearchUseCaseImpl @Inject constructor(private val sharedPrefHelper: SharedPrefHelper) :
    SaveLastSearchUseCase {
    override fun invoke(input: String): Boolean {
        return sharedPrefHelper.saveLastSearch(input)
    }

}

interface GetLastSearchUseCase {
    operator fun invoke(): String
}

class GetLastSearchUseCaseImp @Inject constructor(private val sharedPrefHelper: SharedPrefHelper) :
    GetLastSearchUseCase {
    override fun invoke(): String {
        return sharedPrefHelper.getLastSearch()
    }


}