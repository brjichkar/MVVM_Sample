package com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datastore

import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileRequest
import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileResponse
import retrofit2.Response

interface MyProfileRemoteDataSource {
    suspend fun getMyProfileDataFromRemote(requests: ProfileRequest): Response<ProfileResponse>
}