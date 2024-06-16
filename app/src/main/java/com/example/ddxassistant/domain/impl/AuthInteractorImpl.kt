package com.example.ddxassistant.domain.impl

import com.example.ddxassistant.data.dto.model.Auth
import com.example.ddxassistant.domain.interfaces.AuthInteractor
import com.example.ddxassistant.domain.interfaces.AuthRepository
import com.example.ddxassistant.domain.model.ServerRequestType
import com.example.ddxassistant.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthInteractorImpl(private val repository: AuthRepository): AuthInteractor {
    override fun signIn(name: String, email: String, password: String): Flow<Pair<UserData, ServerRequestType>> {
        return repository.signIn(name, email, password).map { result ->
            when(result.second){
                true -> {
                    Pair(result.first, ServerRequestType.SUCCESS)
                }
                false -> {
                    Pair(UserData(), ServerRequestType.ERROR)
                }
            }
        }
    }

    override fun signUp(name: String, email: String, password: String): Flow<Pair<UserData, ServerRequestType>> {
        return repository.signUp(name, email, password).map { result ->
            when(result.second){
                true -> {
                    Pair(result.first, ServerRequestType.SUCCESS)
                }
                false -> {
                    Pair(UserData(), ServerRequestType.ERROR)
                }
            }
        }
    }
}