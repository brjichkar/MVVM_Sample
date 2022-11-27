package com.neotrick.mvvmsample.api_section

import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginRequest
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("User_check")
    suspend fun processLogin(@Body requests: LoginRequest): Response<LoginResponse>

}