package com.neotrick.mvvmsample.ui_modules.login_module.ui.login.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.neotrick.mvvmsample.R
import com.neotrick.mvvmsample.databinding.ActivityLoginBinding
import com.neotrick.mvvmsample.ui_modules.base_class_section.BaseActivity
import com.neotrick.mvvmsample.ui_modules.login_module.ui.login.data.model.LoginRequest
import com.neotrick.mvvmsample.ui_modules.profile_module.ui.ProfileActivity
import com.neotrick.mvvmsample.utility_section.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private lateinit var mActivityLoginBinding: ActivityLoginBinding

    private lateinit var mLoginViewModel: LoginViewModel

    @Inject
    lateinit var mfactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        mActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        initialSetup()
        setupObserver()
    }

    private fun initialSetup(){
        // work on UI starts
        mActivityLoginBinding.tvInfo.text = Html.fromHtml("<h7>Solapur's own and most prestigious club - <span>The Officer's Club Solpur</span>. World class sports facility at a premium location</h6>", Html.FROM_HTML_MODE_COMPACT)
        mLoginViewModel= ViewModelProvider(this,mfactory)[LoginViewModel::class.java]
        mActivityLoginBinding.btnLogin.setOnClickListener {
            mActivityLoginBinding.apply {
                showLoading()
                mLoginViewModel.getLogin(LoginRequest(LoginRequest.Jsondata(etPhone.text.toString(),etPassword.text.toString())))
            }

        }
    }

    private fun setupObserver(){
        mLoginViewModel.loginResult.observe(this){
            when (it){
                is Resource.Error -> {
                    hideLoading()
                    showMessage(it.message)
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    hideLoading()
                    val mainActIntent = Intent(this, ProfileActivity::class.java)
                    startActivity(mainActIntent)
                    finish()
                }
            }
        }
    }


    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String?) {}

}