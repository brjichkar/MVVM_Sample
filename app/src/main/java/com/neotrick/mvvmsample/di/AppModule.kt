package com.neotrick.mvvmsample.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.neotrick.mvvmsample.BuildConfig
import com.neotrick.mvvmsample.MvvmApplication
import com.neotrick.mvvmsample.api_section.ApiInterface
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.LoginRepositoryImpl
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastore.LoginRemoteDataSource
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastoreImpl.LoginRemoteDataSourceImpl
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.repository.LoginRepository
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.usecase.LoginUseCase
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.ui.LoginViewModelFactory
import com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.MyProfileRepositoryImpl
import com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datasourceImpl.MyProfileCacheDataSourceImpl
import com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datasourceImpl.MyProfileRemoteDataSourceImpl
import com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datastore.MyProfileCacheDataSource
import com.neotrick.mvvmsample.ui_modules.profile_module.data.repository.datastore.MyProfileRemoteDataSource
import com.neotrick.mvvmsample.ui_modules.profile_module.domain.repository.MyProfileRepository
import com.neotrick.mvvmsample.ui_modules.profile_module.domain.usecase.MyProfileUseCase
import com.neotrick.mvvmsample.ui_modules.profile_module.ui.ProfileViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // overall app usage items
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideApplication(): MvvmApplication {
        return MvvmApplication()
    }

    @Provides
    @Singleton
    fun provideContext(application: MvvmApplication, @ApplicationContext context: Context,): Context {
        return context.applicationContext
    }


    // login specific provider
    @Provides
    @Singleton
    fun provideLoginRepository(loginRemoteDataSource: LoginRemoteDataSource): LoginRepository {
        return LoginRepositoryImpl(loginRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(apiInterface: ApiInterface): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(apiInterface)
    }

    @Provides
    @Singleton
    fun provideLoginViewModelFactory(loginUseCase: LoginUseCase): LoginViewModelFactory {
        return LoginViewModelFactory(loginUseCase)
    }

    // my profile providers
    @Provides
    @Singleton
    fun provideMyProfileViewModelFactory(myProfileUseCase: MyProfileUseCase):ProfileViewModelFactory{
        return ProfileViewModelFactory(myProfileUseCase)
    }

    @Singleton
    @Provides
    fun provideMyProfileRemoteDataSourceImpl(apiInterface: ApiInterface): MyProfileRemoteDataSource {
        return MyProfileRemoteDataSourceImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun provideMyProfileCacheDataSourceImpl(): MyProfileCacheDataSource {
        return MyProfileCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideMyProfileRepository(myProfileRemoteDataSource: MyProfileRemoteDataSource,
                               myProfileCacheDataSource: MyProfileCacheDataSource): MyProfileRepository {
        return MyProfileRepositoryImpl(myProfileRemoteDataSource,myProfileCacheDataSource)
    }
}