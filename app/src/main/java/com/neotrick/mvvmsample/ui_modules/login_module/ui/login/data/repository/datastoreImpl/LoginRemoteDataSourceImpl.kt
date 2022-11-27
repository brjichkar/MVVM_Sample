package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastoreImpl

import com.neotrick.mvvmsample.api_section.ApiInterface
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginRequest
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginResponse
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastore.LoginRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface): LoginRemoteDataSource {
    override suspend fun getLoginFromRemote(requests: LoginRequest): Response<LoginResponse> {
        return apiInterface.processLogin(requests)
    }
}