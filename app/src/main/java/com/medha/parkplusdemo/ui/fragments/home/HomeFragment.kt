package com.medha.parkplusdemo.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.medha.parkplusdemo.databinding.FragmentHomeBinding
import com.medha.parkplusdemo.helper.SharedPreferencesHelper
import com.medha.parkplusdemo.ui.activities.WelcomePageActivity
import com.medha.parkplusdemo.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logout.setSafeOnClickListener {
            // logout and clear all backstack
            SharedPreferencesHelper.defaultPrefs().edit().clear().apply()
            startActivityWithClearFlag(Intent(requireContext(),WelcomePageActivity::class.java))
        }
    }
}