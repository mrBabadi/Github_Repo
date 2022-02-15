package com.bbd.github_repo.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.bbd.github_repo.data.source.local.SharedPrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context = appContext

    @Singleton
    @Provides
    fun provideSharedPref(sharedPreferences: SharedPreferences) = SharedPrefHelper(sharedPreferences)

    @Singleton
    @Provides
    fun provideSharedPreferences(appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("app_pref",Context.MODE_PRIVATE)
    }
}