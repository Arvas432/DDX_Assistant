package com.example.ddxassistant.data.network.impl

import android.util.Log
import com.example.ddxassistant.data.dto.model.Auth
import com.example.ddxassistant.data.dto.request.AuthRequestEmailPassword
import com.example.ddxassistant.data.dto.request.SignUpRequestEmailPassword
import com.example.ddxassistant.data.dto.response.Response
import com.example.ddxassistant.data.dto.request.WorkoutDateRequest
import com.example.ddxassistant.data.local.TokenSharedPreferencesManager
import com.example.ddxassistant.data.network.AccountApi
import com.example.ddxassistant.data.network.ExercisesAPI
import com.example.ddxassistant.data.network.NetworkClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val accountService: AccountApi,
    private val exerciseService: ExercisesAPI,
    private val tokenSharedPreferencesManager: TokenSharedPreferencesManager
) : NetworkClient {
    val token = MutableStateFlow("")

    init {
        token.value = tokenSharedPreferencesManager.getToken()
    }

    override suspend fun doRequest(dto: Any): Response {
        if (dto is WorkoutDateRequest) {
            return withContext(Dispatchers.IO) {
                try {
                    val response = exerciseService.getWorkoutsForDate(dto.date)
                    response.apply { resultCode = 200 }
                } catch (e: Throwable) {
                    Response().apply { resultCode = 500 }
                }
            }
        } else if (dto is SignUpRequestEmailPassword) {
            return withContext(Dispatchers.IO) {
                try {
                    val response = accountService.signUp(
                        dto.auth
                    )
                    Log.d("signUp", response.toString())
                    response.apply { resultCode = 200 }

                } catch (e: Exception) {
                    Log.i("network exception", e.toString())
                    Response().apply { resultCode = 500 }
                }
            }
        } else if (dto is AuthRequestEmailPassword) {
            return withContext(Dispatchers.IO) {
                try {
                    val response = accountService.signIn(
                        dto.auth
                    )
                    if (response.auth.error == "") {
                        val receivedToken = response.auth.token.trim()
                        token.value = receivedToken
                        tokenSharedPreferencesManager.saveToken(receivedToken)
                        Log.d("signUp", response.toString())
                        response.apply { resultCode = 200 }
                    } else {
                        Log.i("network exception", response.auth.error)
                        Response().apply { resultCode = 501 }
                    }
                }
                catch (e: Exception) {
                    Log.i("network exception", e.toString())
                    Response().apply { resultCode = 500 }
                }
            }
        }
        else{
            return Response().apply { resultCode = 400 }
        }
    }
}