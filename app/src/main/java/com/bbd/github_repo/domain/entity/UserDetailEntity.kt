package com.bbd.github_repo.domain.entity

data class UserDetailEntity(
    val userId: Int,
    val login: String,
    val userAvatarUrl: String?,
    val userName: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val repos:Int,
    val bio: String?,
    val followers: Int,
    val following: Int,
    val email: String?,
    val pageUrl: String,
    val twitterUserName: String?,
    val joinedDate: String,
    val lastActivityDate: String
)

data class UserExtraInfo(val resourceId: Int, val text: String)
