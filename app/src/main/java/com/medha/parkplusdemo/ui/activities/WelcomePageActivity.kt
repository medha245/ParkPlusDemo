package com.medha.parkplusdemo.ui.activities

import android.content.Intent
import android.os.Bundle
import com.medha.parkplusdemo.Constants
import com.medha.parkplusdemo.helper.SharedPreferencesHelper

/**
 * Splash activity : not setting any layout so it will by
 * default take window background which is defined in splash theme
 */
class WelcomePageActivity : BaseActivity() {
    private val prefs = SharedPreferencesHelper.defaultPrefs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // start onboarding activity and finish this activity
        if(!prefs.getString(Constants.USER_EMAIL,"").isNullOrEmpty()){
            // user has logged in
            startActivityAndFinish(Intent(this, HomePageActivity::class.java))
        }else{
            // new user
            startActivityAndFinish(Intent(this, OnboardingActivity::class.java))
        }

    }
}