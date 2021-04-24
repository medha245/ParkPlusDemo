package com.medha.parkplusdemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.medha.parkplusdemo.databinding.FragmentChooseOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment() {
    private lateinit var binding:FragmentChooseOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentChooseOnboardingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogin.setSafeOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.moveToLoginPage())
        }

        binding.tvSignup.setSafeOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.moveToSignupPage())
        }
    }

}