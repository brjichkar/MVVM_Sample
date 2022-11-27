package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository

import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginRequest
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginResponse
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastore.LoginRemoteDataSource
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.repository.LoginRepository
import com.neotrick.mvvmsample.utility_section.Resource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
): LoginRepository {

    override suspend fun getLogin(requestData: LoginRequest): Resource<LoginResponse> {
        return responseToResource(loginRemoteDataSource.getLoginFromRemote(requestData))
    }

    private fun responseToResource(response: Response<LoginResponse>): Resource<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error("Error Code-" + response.code() + " Message-" + response.message())
    }
}