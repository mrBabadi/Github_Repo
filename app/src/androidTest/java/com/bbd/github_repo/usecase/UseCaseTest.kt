package com.bbd.github_repo.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bbd.github_repo.ResponseFileReader
import com.bbd.github_repo.data.LocalException
import com.bbd.github_repo.data.ResponseState
import com.bbd.github_repo.data.source.remote.api_model.UserSearchResult
import com.bbd.github_repo.domain.ext.toUserSearchEntity
import com.bbd.github_repo.domain.usecase.GetUserDetailsUseCase
import com.bbd.github_repo.domain.usecase.SearchUserUseCase
import com.bbd.github_repo.getHttp404
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
import retrofit2.HttpException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class UseCaseTest {
    private val TAG = "UseCaseTest"

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var searchUseCase: SearchUserUseCase
    @Inject
    lateinit var getUserDetailsUseCase : GetUserDetailsUseCase

    lateinit var gson: Gson

    @Before
    fun setup() {
        rule.inject()
        gson = GsonBuilder().create()
    }

    @Test
    fun checkSearchUseCase_notNull() {
        assertNotNull(searchUseCase)
    }

    @Test
    fun checkGetUserDetailsUseCase_notNull() {
        assertNotNull(getUserDetailsUseCase)
    }

    @Test
    fun testSearch_noResult() {
        val expectedResponse = ResponseFileReader("no_result_for_search.json")
        val expectedList = gson.fromJson(expectedResponse.content, UserSearchResult::class.java)

        searchUseCase.invoke("sdflsflsfjoijiej")
            .test()
            .awaitDone(5, TimeUnit.SECONDS)
            .assertValues(ResponseState.loading(), ResponseState.success(expectedList.toUserSearchEntity()))

    }

    @Test
    fun testGetUserDetails_noUserFound() {
        val response = getUserDetailsUseCase.invoke("sdflsflsfjoijiefsfdj").blockingLast()
        assertEquals(getHttp404().code(),(response.getErrorMessage().throwable as HttpException).code())
    }
}