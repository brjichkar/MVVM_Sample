
package com.neotrick.mvvmsample.utility_section

import android.content.Context
import android.net.ConnectivityManager
import com.neotrick.mvvmsample.MvvmApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import retrofit2.Retrofit
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import com.neotrick.mvvmsample.BuildConfig
import com.neotrick.mvvmsample.api_section.ApiInterface
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.LoginRepositoryImpl
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastore.LoginRemoteDataSource
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.repository.datastoreImpl.LoginRemoteDataSourceImpl
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.repository.LoginRepository
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.domain.usecase.LoginUseCase
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.ui.LoginViewModelFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class NetworkUtils {

    @Provides
    @Singleton
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }






    private val READ_TIMEOUT = 30
    private val WRITE_TIMEOUT = 30
    private val CONNECTION_TIMEOUT = 10
    private val CACHE_SIZE_BYTES = 10 * 1024 * 1024L // 10 MB

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(headerInterceptor)


        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            //hear you can add all headers you want by calling 'requestBuilder.addHeader(name ,  value)'
            it.proceed(requestBuilder.build())
        }
    }


    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }



}