package com.example.ddxassistant.domain.interfaces

import com.example.ddxassistant.data.dto.model.Auth
import com.example.ddxassistant.domain.model.ServerRequestType
import com.example.ddxassistant.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthInteractor {
    fun signIn(name: String, email: String, password: String): Flow<Pair<UserData, ServerRequestType>>
    fun signUp(name: String, email: String, password: String): Flow<Pair<UserData, ServerRequestType>>
}