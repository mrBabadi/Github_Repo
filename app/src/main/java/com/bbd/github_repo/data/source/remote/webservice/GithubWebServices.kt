package com.bbd.github_repo.data.source.remote.webservice

import com.bbd.github_repo.data.source.remote.api_model.UserDetailsResponse
import com.bbd.github_repo.data.source.remote.api_model.UserSearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubWebServices {

    @GET("search/users")
    fun searchUsers(@Query("q") input: String): Single<UserSearchResult>

    @GET("users/{user_name}")
    fun getUserDetails(@Path("user_name") userName: String): Single<UserDetailsResponse>
}