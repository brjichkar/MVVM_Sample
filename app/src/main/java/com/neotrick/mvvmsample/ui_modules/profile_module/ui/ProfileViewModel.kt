package com.neotrick.mvvmsample.ui_modules.profile_module.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileRequest
import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileResponse
import com.neotrick.mvvmsample.ui_modules.profile_module.domain.usecase.MyProfileUseCase
import com.neotrick.mvvmsample.utility_section.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val myProfileUseCase: MyProfileUseCase) : ViewModel(){
    var tempResult : MutableLiveData<Resource<ProfileResponse>> = MutableLiveData()

    fun getLogin(requestData: ProfileRequest) = viewModelScope.launch(Dispatchers.IO) {
        tempResult.postValue(Resource.Loading())
        try{
            val apiResult = myProfileUseCase.execute(requestData)
            tempResult.postValue(apiResult)

        }catch (e: Exception){
            tempResult.postValue(Resource.Error(e.message.toString()))
        }
    }

}