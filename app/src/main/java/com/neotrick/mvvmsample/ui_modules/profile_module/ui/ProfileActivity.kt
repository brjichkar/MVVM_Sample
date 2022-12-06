package com.neotrick.mvvmsample.ui_modules.profile_module.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.neotrick.mvvmsample.R
import com.neotrick.mvvmsample.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var mActivityProfileBinding: ActivityProfileBinding
    private lateinit var mProfileViewModel: ProfileViewModel

    @Inject
    lateinit var mfactory: ProfileViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        mActivityProfileBinding=DataBindingUtil.setContentView(this,R.layout.activity_profile)
        initialSetup()
        setupObserver()
    }

    private fun initialSetup() {
        mProfileViewModel= ViewModelProvider(this,mfactory)[ProfileViewModel::class.java]
    }

    private fun setupObserver() {

    }
}