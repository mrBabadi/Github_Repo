package com.bbd.github_repo.data.source.local

import android.content.SharedPreferences

class SharedPrefHelper(private val preferences: SharedPreferences) {

    private val LAST_SEARCH = "last_search"

    private fun getString(key: String, defaultValue: String = ""): String =
        preferences.getString(key, defaultValue) ?: defaultValue

    private fun putString(key: String, value: String): Boolean =
        preferences.edit().putString(key, value).commit()

    private fun getInt(key: String, defaultValue: Int = -1): Int =
        preferences.getInt(key, defaultValue)

    private fun putInt(key: String, value: Int): Boolean =
        preferences.edit().putInt(key, value).commit()

    private fun getBoolean(key: String, defaultValue: Boolean = false): Boolean =
        preferences.getBoolean(key, defaultValue)

    private fun putBoolean(key: String, value: Boolean): Boolean =
        preferences.edit().putBoolean(key, value).commit()


    fun saveLastSearch(text: String?) : Boolean{
        if (text.isNullOrEmpty()) {
            return false
        }
        return putString(LAST_SEARCH,text)
    }

    fun getLastSearch() = getString(LAST_SEARCH,"")

}