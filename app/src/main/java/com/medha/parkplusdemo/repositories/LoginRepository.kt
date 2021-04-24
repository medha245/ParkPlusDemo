package com.medha.parkplusdemo.repositories

import com.medha.parkplusdemo.ParkService
import com.medha.parkplusdemo.model.LoginData
import com.medha.parkplusdemo.model.SignupModel
import com.medha.parkplusdemo.model.UserDataModel
import com.medha.parkplusdemo.network.BaseRepository
import com.medha.parkplusdemo.network.Result
import okhttp3.ResponseBody
import javax.inject.Inject
import kotlin.math.sign

class LoginRepository @Inject constructor(val api: ParkService) : BaseRepository() {

    suspend fun loginUser(loginData: LoginData):Result<UserDataModel> {
        /*val response = safeApiCall(call = { api.loginUser(loginData).await() },
            errorMessage = "could not login "
        )*/
        // returnding dummy data from here
        val result = UserDataModel(true,"medha","smedha245@gmail.com","2")
        val finalResult = Result.Success(result)
        return finalResult
    }

    suspend fun signupUser(signupData: SignupModel):Result<UserDataModel>{
        /*val response = safeApiCall(call = { api.signupUser(signupData).await() },
            errorMessage = "could not login "
        )
        return response*/

        val result = UserDataModel(true,"medha","smedha245@gmail.com","2")
        val finalResult = Result.Success(result)
        return finalResult
    }

}