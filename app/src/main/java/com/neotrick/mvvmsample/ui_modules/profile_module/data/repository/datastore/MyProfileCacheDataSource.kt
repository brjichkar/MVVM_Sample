package com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datastore

import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileResponse
import com.neotrick.mvvmsample.utility_section.Resource

interface MyProfileCacheDataSource {
    suspend fun getMyProfileDataFromCache(): Resource<ProfileResponse>
    suspend fun updateMyProfileDataToCache(response: Resource<ProfileResponse>)
}