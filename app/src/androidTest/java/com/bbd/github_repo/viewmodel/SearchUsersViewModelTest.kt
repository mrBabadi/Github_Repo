package com.bbd.github_repo.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bbd.github_repo.domain.usecase.GetLastSearchUseCase
import com.bbd.github_repo.domain.usecase.SaveLastSearchUseCase
import com.bbd.github_repo.domain.usecase.SearchUserUseCase
import com.bbd.github_repo.presentation.viewmodel.SearchUsersViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SearchUsersViewModelTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var searchUseCase: SearchUserUseCase

    @Inject
    lateinit var saveLastSearchUseCase: SaveLastSearchUseCase

    @Inject
    lateinit var getLastSearchUseCase: GetLastSearchUseCase

    lateinit var gson: Gson
    lateinit var viewModel: SearchUsersViewModel

    private val waiter: CountDownLatch = CountDownLatch(1)

    @Before
    fun setup() {
        rule.inject()
        gson = GsonBuilder().create()
        viewModel = SearchUsersViewModel(searchUseCase, saveLastSearchUseCase, getLastSearchUseCase)
    }

    @Test
    fun checkSearchUsersViewModel_notNull() {
        assertNotNull(viewModel)
    }

    @Test
    fun test_emptyLastSearch_shouldReturnFalse() {
        assertEquals(false,saveLastSearchUseCase.invoke(""))
    }


    @Test
    fun test_jakeLastSearch_shouldReturn_jake() {
        saveLastSearchUseCase("jake")
        assertEquals("jake", getLastSearchUseCase())
    }

}