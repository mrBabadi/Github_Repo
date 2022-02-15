package com.bbd.github_repo.presentation.viewmodel

import com.bbd.github_repo.domain.usecase.SearchUserUseCase
import com.bbd.github_repo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchUsersViewModel @Inject constructor(private val searchUsersUseCase: SearchUserUseCase) :
    BaseViewModel()  {
}