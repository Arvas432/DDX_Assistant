package com.example.ddxassistant.data.network

import com.example.ddxassistant.data.dto.response.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}