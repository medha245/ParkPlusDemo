package com.medha.parkplusdemo.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.medha.parkplusdemo.Constants
import com.medha.parkplusdemo.databinding.FragmentSignupBinding
import com.medha.parkplusdemo.helper.SharedPreferencesHelper
import com.medha.parkplusdemo.helper.SharedPreferencesHelper.set
import com.medha.parkplusdemo.model.LoginData
import com.medha.parkplusdemo.model.SignupModel
import com.medha.parkplusdemo.network.Result
import com.medha.parkplusdemo.ui.activities.HomePageActivity
import com.medha.parkplusdemo.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sign

@AndroidEntryPoint
class SignupFragment : BaseFragment() {
    private val signupData: SignupModel = SignupModel()
    private lateinit var binding:FragmentSignupBinding
    private val viewModel: LoginViewModel by activityViewModels()
    private val prefs = SharedPreferencesHelper.defaultPrefs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSignupBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilPassword.error = null
                signupData.password = p0.toString()
            }
        })

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilEmail.error = null
                signupData.email = p0.toString()
            }
        })

        binding.etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilConfirmPassword.error = null
                signupData.confirmPassword = p0.toString()
            }
        })

        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilEmail.error = null
                signupData.name = p0.toString()
            }
        })

        binding.ctaSignup.setSafeOnClickListener {
            // validate data
            if(!signupData.isPasswordValid(signupData.password)){
                binding.tilPassword.error = "Password can't be less than 8 characters"
                return@setSafeOnClickListener
            }
            if(!signupData.doPasswordsMatch(signupData.password,signupData.confirmPassword)){
                binding.tilConfirmPassword.error = "Passwords do not match, both must be of 8 characters"
                return@setSafeOnClickListener
            }
            if(!signupData.isValidEmail(signupData.email)){
                binding.tilEmail.error = "Please enter a valid email"
                return@setSafeOnClickListener
            }
            if(!signupData.isValidName(signupData.name)){
                binding.tilName.error = "Please enter a valid name"
                return@setSafeOnClickListener
            }

            viewModel.signupUser(signupData)

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Result.Success -> {
                    // store data in preference and clear all previous activity
                    prefs.set(Constants.USER_NAME,it.data.name)
                    prefs.set(Constants.USER_EMAIL,it.data.email)
                    prefs.set(Constants.PLAN_ID,it.data.planId)
                    startActivityWithClearFlag(Intent(requireContext(),HomePageActivity::class.java))
                }

                is Result.Error -> {
                    Toast.makeText(requireContext(),"Could not login", Toast.LENGTH_SHORT).show()
                }

                is Result.Failure -> {
                    Toast.makeText(requireContext(),"Could not login", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}