package com.neotrick.mvvmsample.ui_modules.profile_module.data.repository

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileRequest
import com.neotrick.mvvmsample.ui_modules.profile_module.data.model.ProfileResponse
import com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datastore.MyProfileCacheDataSource
import com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datastore.MyProfileRemoteDataSource
import com.neotrick.mvvmsample.ui_modules.profile_module.domain.repository.MyProfileRepository
import com.neotrick.mvvmsample.utility_section.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class MyProfileRepositoryImpl @Inject constructor(
    private val myProfileRemoteDataSource: MyProfileRemoteDataSource,
    private val myProfileCacheDataSource: MyProfileCacheDataSource
):MyProfileRepository {

    override suspend fun getMyProfile(requestData: ProfileRequest): Resource<ProfileResponse> {
        return getMyProfileDataFromCache(requestData)
    }

    override suspend fun updateMyProfileData(requestData: ProfileRequest): Resource<ProfileResponse> {
        val remoteResponse=getMyProfileFromRemote(requestData)
        myProfileCacheDataSource.updateMyProfileDataToCache(remoteResponse)
        return remoteResponse
    }


    private suspend fun getMyProfileDataFromCache(requestData: ProfileRequest): Resource<ProfileResponse>{
        var cacheResponse:Resource<ProfileResponse>?=null
        try {
            cacheResponse=myProfileCacheDataSource.getMyProfileDataFromCache()
        }
        catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
        if(cacheResponse!=null){
            return cacheResponse
        }
        else{
            cacheResponse=getMyProfileFromRemote(requestData)
            myProfileCacheDataSource.updateMyProfileDataToCache(cacheResponse)
        }
        return cacheResponse
    }

    private suspend fun getMyProfileFromRemote(requestData: ProfileRequest): Resource<ProfileResponse>{
        return responseToResource(myProfileRemoteDataSource.getMyProfileDataFromRemote(requestData))
    }
    private fun responseToResource(response: Response<ProfileResponse>): Resource<ProfileResponse>{
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error("Error Code-" + response.code() + " Message-" + response.message())
    }
}