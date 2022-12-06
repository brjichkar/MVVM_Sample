package com.neotrick.mvvmsample.ui_modules.profile_module.domain.usecase

import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileRequest
import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileResponse
import com.neotrick.mvvmsample.ui_modules.profile_module.domain.repository.MyProfileRepository
import com.neotrick.mvvmsample.utility_section.Resource
import javax.inject.Inject

class MyProfileUseCase @Inject constructor(private val myProfileRepository: MyProfileRepository){
    suspend fun execute(requestData:ProfileRequest): Resource<ProfileResponse>{
        return myProfileRepository.getMyProfile(requestData)
    }
}