package com.bbd.github_repo.di

import com.bbd.github_repo.data.repository.ApiRepository
import com.bbd.github_repo.data.source.local.SharedPrefHelper
import com.bbd.github_repo.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideSearchUserUseCase(apiRepository: ApiRepository) : SearchUserUseCase {
        return SearchUserUseCaseImpl(apiRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserDetailsUseCase(apiRepository: ApiRepository) : GetUserDetailsUseCase {
        return GetUserDetailsUseCaseImpl(apiRepository)
    }

    @Singleton
    @Provides
    fun provideSaveLastSearchUseCase(sharedPrefHelper: SharedPrefHelper): SaveLastSearchUseCase {
        return SaveLastSearchUseCaseImpl(sharedPrefHelper)
    }

    @Singleton
    @Provides
    fun provideGetLastSearchUseCase(sharedPrefHelper: SharedPrefHelper): GetLastSearchUseCase {
        return GetLastSearchUseCaseImp(sharedPrefHelper)
    }
}