package com.bbd.github_repo.domain.entity

data class UserSearchEntity(val userOverviews: List<UserOverviewEntity>)

data class UserOverviewEntity(val userId: Int, val userName: String, val userAvatarUrl: String?)