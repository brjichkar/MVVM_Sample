package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.usecase

import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginRequest
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginResponse
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.repository.LoginRepository
import com.neotrick.mvvmsample.utility_section.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun execute(requestData: LoginRequest): Resource<LoginResponse> {
        return loginRepository.getLogin(requestData)
    }
}