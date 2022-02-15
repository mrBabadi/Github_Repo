package com.bbd.github_repo.domain.ext

import com.bbd.github_repo.data.source.remote.api_model.UserDetailsResponse
import com.bbd.github_repo.data.source.remote.api_model.UserSearchResult
import com.bbd.github_repo.domain.entity.UserDetailEntity
import com.bbd.github_repo.domain.entity.UserOverviewEntity
import com.bbd.github_repo.domain.entity.UserSearchEntity

fun UserSearchResult.toUserSearchEntity() = UserSearchEntity(this.items.map { item ->
    UserOverviewEntity(
        item.id,
        item.login,
        item.avatarUrl
    )
})

fun UserDetailsResponse.toUserDetailsEntity() = UserDetailEntity(
    userId = this.id,
    login = this.login,
    userAvatarUrl = this.avatarUrl,
    userName = this.name,
    company = this.company,
    blog = this.blog,
    location = this.location,
    repos = this.publicRepos,
    bio = this.bio,
    followers = this.followers,
    following = this.following,
    email = this.email,
    pageUrl = this.htmlUrl,
    twitterUserName = this.twitterUsername,
    joinedDate = this.createdAt,
    lastActivityDate = this.updatedAt
)