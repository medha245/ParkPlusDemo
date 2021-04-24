package com.medha.parkplusdemo.ui.activities

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.medha.parkplusdemo.R
import com.medha.parkplusdemo.databinding.ActivityHomePageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageActivity : BaseActivity() {
    private lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        // get nav host fragment from xml
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // set up nav graph with bottom navigation view
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            // this is to avoid multiple instances of same fragment on click of bottom menu
            NavigationUI.onNavDestinationSelected(
                item,
                Navigation.findNavController(this, R.id.nav_host_fragment)
            )

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // to support back button press
        return findNavController(R.id.nav_host_fragment).navigateUp()
                || super.onSupportNavigateUp()
    }


}