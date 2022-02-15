package com.bbd.github_repo


import com.google.gson.annotations.SerializedName

data class TestModel(
    @SerializedName("data")
    val `data`: String
)