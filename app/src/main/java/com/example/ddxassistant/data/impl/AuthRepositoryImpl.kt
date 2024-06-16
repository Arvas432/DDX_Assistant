package com.example.ddxassistant.data.impl

import android.util.Log
import com.example.ddxassistant.data.dto.model.Auth
import com.example.ddxassistant.data.dto.request.AuthRequestEmailPassword
import com.example.ddxassistant.data.dto.request.SignUpRequestEmailPassword
import com.example.ddxassistant.data.dto.response.AuthorizationResponse
import com.example.ddxassistant.data.dto.response.WorkoutDateResponse
import com.example.ddxassistant.data.network.NetworkClient
import com.example.ddxassistant.domain.interfaces.AuthRepository
import com.example.ddxassistant.domain.model.Exercise
import com.example.ddxassistant.domain.model.UserData
import com.example.ddxassistant.domain.model.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(val networkClient: NetworkClient): AuthRepository {
    override fun signIn(name: String, email: String, password: String): Flow<Pair<UserData, Boolean>> = flow{
        val response = networkClient.doRequest(AuthRequestEmailPassword(Auth(name, email, password)))
        when(response.resultCode){
            200 ->{
                Log.i("RESPONSE", response.toString())
                with(response as AuthorizationResponse){
                    val data = UserData(response.auth.name, response.auth.email, response.auth.role)
                    emit(Pair(data, true))
                }
            } else -> {
                emit(Pair(UserData(), false))
            }
        }
    }

    override fun signUp(name: String, email: String, password: String): Flow<Pair<UserData, Boolean>> = flow {
        val response = networkClient.doRequest(SignUpRequestEmailPassword(Auth(name, email, password)))
        when(response.resultCode){
            200 ->{
                Log.i("RESPONSE", response.toString())
                with(response as AuthorizationResponse){
                    val data = UserData(response.auth.name, response.auth.email, response.auth.role)
                    emit(Pair(data, true))
                }
            } else -> {
            emit(Pair(UserData(), false))
        }
        }
    }

}