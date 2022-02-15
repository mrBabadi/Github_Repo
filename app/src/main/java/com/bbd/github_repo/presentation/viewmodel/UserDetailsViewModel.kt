package com.bbd.github_repo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.bbd.github_repo.R
import com.bbd.github_repo.data.ResponseState
import com.bbd.github_repo.domain.entity.UserDetailEntity
import com.bbd.github_repo.domain.entity.UserExtraInfo
import com.bbd.github_repo.domain.usecase.GetUserDetailsUseCase
import com.bbd.github_repo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(private val getUserDetailsUseCase: GetUserDetailsUseCase) :
    BaseViewModel() {

    fun getUserDetails(userName: String): LiveData<ResponseState<UserDetailEntity>> {
        return LiveDataReactiveStreams.fromPublisher(getUserDetailsUseCase(userName))
    }

    fun getUserExtraInfo(data: UserDetailEntity): ArrayList<UserExtraInfo> {
        return arrayListOf<UserExtraInfo>().apply {
            if (!data.company.isNullOrEmpty()) {
                add(UserExtraInfo(R.drawable.ic_baseline_apartment_24, data.company))
            }
            if (!data.location.isNullOrEmpty()) {
                add(UserExtraInfo(R.drawable.ic_baseline_location_on_24, data.location))
            }
            if (!data.blog.isNullOrEmpty()) {
                add(UserExtraInfo(R.drawable.ic_baseline_link_24, data.blog))
            }
            if (!data.email.isNullOrEmpty()) {
                add(UserExtraInfo(R.drawable.ic_baseline_email_24, data.email))
            }
        }
    }
}