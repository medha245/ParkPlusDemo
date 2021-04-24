package com.medha.parkplusdemo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.medha.parkplusdemo.model.LoginData
import com.medha.parkplusdemo.model.SignupModel
import com.medha.parkplusdemo.model.UserDataModel
import com.medha.parkplusdemo.network.Result
import com.medha.parkplusdemo.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel(){
    private val parentJob = SupervisorJob()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    private val scope = CoroutineScope(coroutineContext)

    var userLiveData = MutableLiveData<Result<UserDataModel>>()

    fun loginUser(loginData: LoginData){
        scope.launch {
            val response = repository.loginUser(loginData)
            userLiveData.postValue(response)
        }
    }

    fun signupUser(signupData: SignupModel){
        scope.launch {
            val response = repository.signupUser(signupData)
            userLiveData.postValue(response)
        }
    }

}