package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.usecase.LoginUseCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


class LoginViewModelFactory @Inject constructor(private val loginUseCase: LoginUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginUseCase) as T
    }
}