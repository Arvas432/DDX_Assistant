package com.example.ddxassistant.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentAuthBinding
import com.example.ddxassistant.domain.model.UserData
import com.example.ddxassistant.ui.viewModels.AuthViewModel
import com.example.ddxassistant.ui.states.AuthScreenStates
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthFragment :BindingFragment<FragmentAuthBinding>(){
    private var isCoach: Boolean = false
    private var passwordHidden = true
    private val viewModel by viewModel<AuthViewModel>()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAuthBinding {
        return FragmentAuthBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner){
            renderState(it)

        }
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
            when(passwordHidden){
                true ->{showPassword(); passwordHidden = false}
                false ->{hidePassword(); passwordHidden = true }
            }
        }
        val loginFieldTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setUsername(s.toString())
                viewModel.setEmail(s.toString())
            }

            override fun afterTextChanged(s: Editable?) = Unit

        }
        val passwordFieldTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setPassword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) = Unit

        }
        binding.idField.addTextChangedListener(loginFieldTextWatcher)
        binding.passwordField.addTextChangedListener(passwordFieldTextWatcher)
        binding.startBtn.setOnClickListener {
            viewModel.signIn()
        }

    }
    private fun renderState(state: AuthScreenStates){

        when(state){
            is AuthScreenStates.Loading -> renderLoading()
            is AuthScreenStates.LoginFailed -> renderFailedLogin()
            is AuthScreenStates.LoginSuccessful -> renderSuccessfulLogin(state.data)
            is AuthScreenStates.Default -> renderDefault()
        }
    }
    private fun renderLoading(){

    }
    private fun renderFailedLogin(){
        Toast.makeText(requireContext(), "Авторизация не удалась, но мы вас пустим", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_authFragment_to_trainingScheduleFragment)
    }
    private fun renderSuccessfulLogin(data: UserData){
        Toast.makeText(requireContext(), "Авторизация прошла успешно", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_authFragment_to_trainingScheduleFragment)
    }
    private fun renderDefault(){

    }
    private fun renderCoach(){
        binding.idField.hint = resources.getString(R.string.id)
        binding.noAccountLayout.isVisible = false
    }
    private fun renderClient(){
        binding.idField.hint = resources.getString(R.string.login)
        binding.noAccountLayout.isVisible = true
    }
    private fun showPassword(){
        binding.eyeButton.setImageResource(R.drawable.eye_open)
        binding.passwordField.transformationMethod = HideReturnsTransformationMethod.getInstance()
        binding.passwordField.setSelection(binding.passwordField.text!!.length)
    }
    private fun hidePassword(){
        binding.eyeButton.setImageResource(R.drawable.eye_closed)
        binding.passwordField.transformationMethod = PasswordTransformationMethod.getInstance()
        binding.passwordField.setSelection(binding.passwordField.text!!.length)
    }
    companion object{
        const val ROLE_KEY = "ROLE_KEY"
        const val USER_DATA_KEY = "USER_DATA_KEY"
    }

}