package com.example.ddxassistant.ui

import com.example.ddxassistant.domain.model.UserData

sealed class AuthScreenStates {
    object Default: AuthScreenStates()
    object LoginFailed: AuthScreenStates()
    object Loading: AuthScreenStates()
    data class LoginSuccessful(val data: UserData) : AuthScreenStates()
}