package com.example.ddxassistant.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentRegistrationBinding
import com.example.ddxassistant.domain.model.UserData
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegistrationFragment : BindingFragment<FragmentRegistrationBinding>(){
    private var isCoach: Boolean = false
    private val viewModel by viewModel<AuthViewModel>()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner){
            renderState(it)
        }
        binding.alreadyHaveAnAccountLayout.setOnClickListener {
            findNavController().navigateUp()
        }
        if(arguments!=null){
            isCoach = requireArguments().getBoolean(ROLE_KEY)
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
        binding.emailField.addTextChangedListener(loginFieldTextWatcher)
        binding.passwordField.addTextChangedListener(passwordFieldTextWatcher)
        binding.registrationButton.setOnClickListener {
            if (binding.passwordField.text == binding.repeatPasswordField.text){
                viewModel.signUp()
            } else{
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
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
        Toast.makeText(requireContext(), "Регистрация не удалась", Toast.LENGTH_SHORT).show()
    }
    private fun renderSuccessfulLogin(data: UserData){
        Toast.makeText(requireContext(), "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_registrationFragment_to_trainingScheduleFragment)
    }
    private fun renderDefault(){

    }
    companion object{
        const val ROLE_KEY = "ROLE_KEY"
        const val USER_DATA_KEY = "USER_DATA_KEY"
    }
}