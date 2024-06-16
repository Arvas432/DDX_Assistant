package com.example.ddxassistant.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.ddxassistant.domain.interfaces.AuthInteractor
import com.example.ddxassistant.domain.model.ServerRequestType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authInteractor: AuthInteractor
) : ViewModel() {
    var textUsername: String = ""
    var textEmail: String = ""
    var textPassword: String = ""
    private var screenStateLivedata = MutableLiveData<AuthScreenStates>(AuthScreenStates.Default)
    fun getScreenStateLiveData(): LiveData<AuthScreenStates> = screenStateLivedata
    fun renderState(state: AuthScreenStates) {
        screenStateLivedata.postValue(state)
    }

    fun setUsername(input: String) {
        textUsername = input
    }

    fun setEmail(input: String) {
        textEmail = input
    }

    fun setPassword(input: String) {
        textPassword = input
    }

    fun signIn() {
        viewModelScope.launch {
            authInteractor.signIn(textUsername, textEmail, textPassword)
                .collect { response ->
                    when (response.second) {
                        ServerRequestType.SUCCESS -> {
                            renderState(AuthScreenStates.LoginSuccessful(response.first))
                        }

                        ServerRequestType.LOADING -> {
                            renderState(AuthScreenStates.Loading)
                        }

                        ServerRequestType.ERROR -> {
                            renderState(AuthScreenStates.LoginFailed)
                        }

                        ServerRequestType.EMPTY -> Unit
                    }
                }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            authInteractor.signUp(textUsername, textEmail, textPassword)
                .collect { response ->
                    when (response.second) {
                        ServerRequestType.SUCCESS -> {
                            renderState(AuthScreenStates.LoginSuccessful(response.first))
                        }

                        ServerRequestType.LOADING -> {
                            renderState(AuthScreenStates.Loading)
                        }

                        ServerRequestType.ERROR -> {
                            renderState(AuthScreenStates.LoginFailed)
                        }

                        ServerRequestType.EMPTY -> Unit
                    }
                }
        }
    }
}
