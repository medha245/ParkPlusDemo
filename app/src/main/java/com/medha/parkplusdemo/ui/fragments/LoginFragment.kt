package com.medha.parkplusdemo.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.medha.parkplusdemo.Constants
import com.medha.parkplusdemo.databinding.FragmentLoginBinding
import com.medha.parkplusdemo.helper.SharedPreferencesHelper
import com.medha.parkplusdemo.helper.SharedPreferencesHelper.set
import com.medha.parkplusdemo.model.LoginData
import com.medha.parkplusdemo.network.Result
import com.medha.parkplusdemo.ui.activities.HomePageActivity
import com.medha.parkplusdemo.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private val viewModel:LoginViewModel by activityViewModels()
    private val loginData:LoginData = LoginData()
    private lateinit var binding: FragmentLoginBinding
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
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etPassword.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // remove error when user starts typing
                binding.tilPassword.error = null
                loginData.password = p0.toString()
            }
        })

        binding.etUsername.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilUsername.error = null
                loginData.username = p0.toString()
            }
        })

        binding.ctaLogin.setSafeOnClickListener {
            if(!loginData.isValidEmail(loginData.username)){
                binding.tilUsername.error = "Please enter a valid email"
                return@setSafeOnClickListener
            }
            if(!loginData.isPasswordValid(loginData.password)){
                binding.tilPassword.error = "Password can't be less than 8 characters"
                return@setSafeOnClickListener

            }
                viewModel.loginUser(loginData)

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Result.Success -> {
                    prefs.set(Constants.USER_NAME,it.data.name)
                    prefs.set(Constants.USER_EMAIL,it.data.email)
                    prefs.set(Constants.PLAN_ID,it.data.planId)
                    startActivityWithClearFlag(
                        Intent(requireContext(),
                            HomePageActivity::class.java)
                    )
                }

                is Result.Error -> {
                    Toast.makeText(requireContext(),"Could not login",Toast.LENGTH_SHORT).show()
                }

                is Result.Failure -> {
                    Toast.makeText(requireContext(),"Could not login",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}