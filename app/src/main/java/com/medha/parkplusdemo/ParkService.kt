package com.medha.parkplusdemo

import com.medha.parkplusdemo.model.LoginData
import com.medha.parkplusdemo.model.SignupModel
import com.medha.parkplusdemo.model.UserDataModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ParkService {

    @POST("/v1/login")
     fun loginUser(@Body data:LoginData ): Deferred<Response<UserDataModel>>

    @POST("/v1/signup")
     fun signupUser(@Body data:SignupModel ): Deferred<Response<UserDataModel>>

}