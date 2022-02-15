package com.bbd.github_repo

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

fun getHttp404() : HttpException{
    return retrofit2.adapter.rxjava2.HttpException(
        Response.error<ResponseBody>(
            404,
            ResponseBody.create(null, "some content")
        )
    )
}