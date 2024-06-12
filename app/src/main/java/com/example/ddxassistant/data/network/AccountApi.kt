package com.example.ddxassistant.data.network

import com.example.ddxassistant.data.dto.Auth
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {
    @POST("auth/signup")
    suspend fun signUp(
        @Body auth: Auth
    ): Response<Auth>

    @POST("auth/signin")
    suspend fun signIn(
        @Body auth: Auth
    ): Response<Auth>
}