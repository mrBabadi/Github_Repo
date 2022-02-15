package com.bbd.github_repo.presentation.viewmodel

import com.bbd.github_repo.domain.usecase.GetUserDetailsUseCase
import com.bbd.github_repo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(private val getUserDetailsUseCase: GetUserDetailsUseCase) :
    BaseViewModel()  {
}