package com.medha.parkplusdemo.network

import android.util.Log
import retrofit2.Response


open class BaseRepository {
    private val TAG = "BaseRepository"

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): Result<T> {
        val result: Result<T> = safeApiResult(call, errorMessage)
        return result
    }

    /**
     * suspend fun to maKe api calls
     */
    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>, errorMessage: String): Result<T> {

        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return Result.Success(response.body()!!, response.raw().request())
            } else return Result.Error(response.errorBody(), response.raw().request())
        } catch (ex: Throwable) {
            Log.e(TAG, ex.toString())
            return Result.Failure(ex.message)
        }
    }
}