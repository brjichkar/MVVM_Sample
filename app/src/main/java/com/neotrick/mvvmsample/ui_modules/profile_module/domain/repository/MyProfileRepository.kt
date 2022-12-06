package com.neotrick.mvvmsample.ui_modules.profile_module.domain.repository

import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileRequest
import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileResponse
import com.neotrick.mvvmsample.utility_section.Resource

interface MyProfileRepository {
    suspend fun getMyProfile(requestData:ProfileRequest): Resource<ProfileResponse>
    suspend fun updateMyProfileData(requestData:ProfileRequest): Resource<ProfileResponse>
}