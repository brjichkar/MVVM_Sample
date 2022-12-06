package com.neotrick.mvvmsample.ui_modules.profile_module.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neotrick.mvvmsample.ui_modules.profile_module.domain.usecase.MyProfileUseCase
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(private val myProfileUseCase: MyProfileUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(myProfileUseCase) as T
    }
}