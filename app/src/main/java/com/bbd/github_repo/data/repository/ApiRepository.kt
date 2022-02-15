package com.bbd.github_repo.data.repository

import com.bbd.github_repo.data.source.remote.webservice.GithubWebServices
import javax.inject.Inject

class ApiRepository @Inject constructor(private val githubWebServices: GithubWebServices) {

    fun searchUser(input: String) = githubWebServices.searchUsers(input)

    fun getUserDetails(userName:String) = githubWebServices.getUserDetails(userName)
}