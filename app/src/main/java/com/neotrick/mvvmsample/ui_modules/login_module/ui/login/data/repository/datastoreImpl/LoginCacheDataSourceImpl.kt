package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastoreImpl

import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginResponse
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastore.LoginCacheDataSource
import com.neotrick.mvvmsample.utility_section.Resource

class LoginCacheDataSourceImpl: LoginCacheDataSource {
    private lateinit var receivedResponse: Resource<LoginResponse>

    override suspend fun getLoginFromCache(): Resource<LoginResponse> {
        return receivedResponse
    }

    override suspend fun updateLoginFromCache(response: Resource<LoginResponse>) {
        receivedResponse=response
    }
}