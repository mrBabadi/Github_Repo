package com.bbd.github_repo.repository

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bbd.github_repo.ResponseFileReader
import com.bbd.github_repo.data.repository.ApiRepository
import com.bbd.github_repo.data.source.remote.api_model.UserDetailsResponse
import com.bbd.github_repo.data.source.remote.api_model.UserSearchResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.HttpException
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ApiRepositoryTest {

    private val TAG = "ApiRepositoryTest"

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: ApiRepository

    lateinit var gson: Gson

    @Before
    fun setup() {
        gson = GsonBuilder().create()
        rule.inject()
    }


    @Test
    fun checkRepository_notNull() {
        assertNotNull(repository)
    }


    @Test
    fun testSearchUsersWithNameJ() {
        val expectedResponse = ResponseFileReader("search_for_j_response.json")
        val expectedList = gson.fromJson(expectedResponse.content, UserSearchResult::class.java)
        val actual = repository.searchUser("j").blockingGet()
        println("Size of the list: ${actual.items.size}")
        assertEquals(expectedList.items.size, actual.items.size)
    }

    @Test
    fun testSearchFakeUser_shouldReturnSizeZero() {
        val actual = repository.searchUser("sdflsflsfjoijiej").blockingGet()
        println("Size of the list: $actual")
        Assert.assertEquals(0, actual.totalCount)
    }

    @Test
    fun testGetJakeWhartonDetails() {
        val expectedResponse = ResponseFileReader("jake_wharton_details.json")
        val expectedItem = gson.fromJson(expectedResponse.content, UserDetailsResponse::class.java)
        val actual = repository.getUserDetails("JakeWharton").blockingGet()
        Log.d(TAG, "testGetJakeWhartonDetails: ${actual.login}")
        assertEquals(expectedItem.login, actual.login)
    }

    @Test
    fun testNoUserFound() {
        repository.getUserDetails("sdflsflsfjoijiefsfdj").test().await().assertError {
            it is HttpException
        }.dispose()
    }


}