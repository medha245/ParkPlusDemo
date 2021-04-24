package com.medha.parkplusdemo.ui.activities

import android.os.Bundle
import androidx.navigation.findNavController
import com.medha.parkplusdemo.R
import com.medha.parkplusdemo.databinding.ActivityOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : BaseActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navContainerOnboarding).navigateUp()
                || super.onSupportNavigateUp()
    }

}

