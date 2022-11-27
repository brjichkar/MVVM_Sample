package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model

data class LoginResponse(
    val `data`: Data,
    val status: String // Success
) {
    data class Data(
        val mobile: String, // 8087027127
        val name: String, // Ritesh Sukhadev Dayama
        val profile: String, // 1
        val user_id: String // 1
    )
}