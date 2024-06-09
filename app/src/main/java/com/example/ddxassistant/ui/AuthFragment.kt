package com.example.ddxassistant.ui

import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentAuthBinding


class AuthFragment :BindingFragment<FragmentAuthBinding>(){
    private var isCoach: Boolean = false
    private var passwordHidden = true
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAuthBinding {
        return FragmentAuthBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments!=null){
            isCoach = requireArguments().getBoolean(ROLE_KEY)
        }
        when(isCoach){
            true -> renderCoach()
            false -> renderClient()
        }
        binding.noAccountLayout.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registrationFragment)
        }
        binding.eyeButton.setOnClickListener {
            passwordHidden = !passwordHidden
            when(passwordHidden){
                true-> hidePassword()
                false -> showPassword()
            }

        }

    }
    private fun renderCoach(){
        binding.idField.hint = resources.getString(R.string.id)
        binding.noAccountLayout.isVisible = false
    }
    private fun renderClient(){
        binding.idField.hint = resources.getString(R.string.email)
        binding.noAccountLayout.isVisible = true
    }
    private fun showPassword(){
        binding.eyeButton.setImageResource(R.drawable.eye_open)
        binding.passwordField.transformationMethod = PasswordTransformationMethod.getInstance()
        binding.passwordField.setSelection(binding.passwordField.text!!.length)
    }
    private fun hidePassword(){
        binding.eyeButton.setImageResource(R.drawable.eye_closed)
        binding.passwordField.transformationMethod = HideReturnsTransformationMethod.getInstance()
        binding.passwordField.setSelection(binding.passwordField.text!!.length)
    }
    companion object{
        const val ROLE_KEY = "ROLE_KEY"
    }

}