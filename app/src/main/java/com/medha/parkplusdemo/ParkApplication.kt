package com.medha.parkplusdemo

import android.app.Application
import android.content.Context
import com.medha.parkplusdemo.helper.SharedPreferencesHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ParkApplication : Application(){

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SharedPreferencesHelper.context = this
    }

}