package com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datasourceImpl

import com.neotrick.mvvmsample.api_section.ApiInterface
import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileRequest
import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileResponse
import com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datastore.MyProfileRemoteDataSource
import com.neotrick.mvvmsample.utility_section.Resource
import retrofit2.Response
import javax.inject.Inject

class MyProfileRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface): MyProfileRemoteDataSource {
    override suspend fun getMyProfileDataFromRemote(requests: ProfileRequest): Response<ProfileResponse> {
        return apiInterface.processProfile(requests)
    }
}