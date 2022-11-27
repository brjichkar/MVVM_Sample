package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginRequest
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginResponse
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.usecase.LoginUseCase
import com.neotrick.mvvmsample.utility_section.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel(){
    var loginResult : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun getLogin(requestData: LoginRequest) = viewModelScope.launch(Dispatchers.IO) {
        loginResult.postValue(Resource.Loading())
        try{
            val apiResult = loginUseCase.execute(requestData)
            loginResult.postValue(apiResult)

        }catch (e: Exception){
            loginResult.postValue(Resource.Error(e.message.toString()))
        }
    }

}