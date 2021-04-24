package com.medha.parkplusdemo.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.medha.parkplusdemo.Constants
import com.medha.parkplusdemo.databinding.FragmentSecondBinding
import com.medha.parkplusdemo.helper.SharedPreferencesHelper
import com.medha.parkplusdemo.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : BaseFragment() {
    private lateinit var binding: FragmentSecondBinding
    private val prefs = SharedPreferencesHelper.defaultPrefs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // SHOW STORED DATA OF PREFS
        binding.message.text = "Second Fragment\n${prefs.getString(Constants.USER_NAME,"")}\n" +
                "${prefs.getString(Constants.USER_EMAIL,"")}"
    }
}