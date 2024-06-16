package com.example.ddxassistant.domain.interfaces

import com.example.ddxassistant.data.dto.model.Auth
import com.example.ddxassistant.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(name: String, email: String, password: String): Flow<Pair<UserData, Boolean>>
    fun signUp(name: String, email: String, password: String): Flow<Pair<UserData, Boolean>>
}