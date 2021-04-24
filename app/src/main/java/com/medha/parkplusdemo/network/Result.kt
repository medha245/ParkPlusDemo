package com.medha.parkplusdemo.network

import okhttp3.Request
import okhttp3.ResponseBody

/**
 * sealed class to identify three types of network response : error , failure and success
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T, val request: Request? = null) : Result<T>()
    data class Error<out T : Any>(val data: ResponseBody?, val request: Request? = null) : Result<T>()
    data class Failure<out T : Any>(val data: String?, val request: Request? = null) : Result<T>()
}