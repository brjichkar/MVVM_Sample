package com.neotrick.mvvmsample

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MvvmApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
    }

}