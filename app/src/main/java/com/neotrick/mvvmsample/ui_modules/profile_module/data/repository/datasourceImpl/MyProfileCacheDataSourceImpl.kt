package com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datasourceImpl

import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileResponse
import com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datastore.MyProfileCacheDataSource
import com.neotrick.mvvmsample.utility_section.Resource

class MyProfileCacheDataSourceImpl: MyProfileCacheDataSource {
    private lateinit var receivedResponse: Resource<ProfileResponse>

    override suspend fun getMyProfileDataFromCache(): Resource<ProfileResponse> {
        return receivedResponse
    }

    override suspend fun updateMyProfileDataToCache(response: Resource<ProfileResponse>){
        receivedResponse=response
    }
}