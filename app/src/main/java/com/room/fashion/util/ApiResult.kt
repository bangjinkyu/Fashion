package com.room.fashion.util

sealed class ApiResult<out R: Any> {
    data class Loading(val message: String) : ApiResult<Nothing>()
    data class Success<out T: Any>(val data: T) : ApiResult<T>()
    data class Error(val error: String) : ApiResult<Nothing>()
}
