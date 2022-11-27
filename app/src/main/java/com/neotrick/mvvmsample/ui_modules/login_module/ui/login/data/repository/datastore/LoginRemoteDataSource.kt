package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastore

import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginRequest
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginResponse
import retrofit2.Response

interface LoginRemoteDataSource {
    suspend fun getLoginFromRemote(requests: LoginRequest): Response<LoginResponse>
}