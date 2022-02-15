package com.bbd.github_repo.data


data class ResponseState<out T> constructor(
    private val inProgress: Boolean = false,
    private val errorMessage: LocalException? = null,
    private val data: T? = null
) {
    companion object {
        fun loading() = ResponseState(inProgress = true, data = null)
        fun <T> success(dataModel: T?) = ResponseState(data = dataModel)
        fun error(error: LocalException) = ResponseState(errorMessage = error, data = null)
    }

    fun isLoading() = inProgress
    fun isError() = errorMessage != null
    fun isSuccess() = data != null && !isError()
    fun isEmpty() = !inProgress && errorMessage == null && !isSuccess()

    fun getErrorMessage(): LocalException {
        if (errorMessage != null) {
            return errorMessage
        }
        throw IllegalStateException("errorMessage shouldn't be null")
    }

    fun getData(): T {
        if (data != null) {
            return data
        }
        throw IllegalStateException("data shouldn't be null")
    }
}

data class LocalException(val throwable: Throwable, val httpCode: Int? = -1) : Exception()