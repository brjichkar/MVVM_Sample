package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.repository

import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginRequest
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginResponse
import com.neotrick.mvvmsample.utility_section.Resource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

interface LoginRepository {
    suspend fun getLogin(requestData: LoginRequest): Resource<LoginResponse>
}