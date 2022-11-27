package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastore

import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginResponse
import com.neotrick.mvvmsample.utility_section.Resource

interface LoginCacheDataSource {
    suspend fun getLoginFromCache(): Resource<LoginResponse>
    suspend fun updateLoginFromCache(response : Resource<LoginResponse>)
}