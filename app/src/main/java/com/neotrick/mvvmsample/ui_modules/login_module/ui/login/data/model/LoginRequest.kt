package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model

data class LoginRequest(
    val jsondata: Jsondata
) {
    data class Jsondata(
        val mobile: String, // 8087027127
        val password: String // 8087027127
    )
}