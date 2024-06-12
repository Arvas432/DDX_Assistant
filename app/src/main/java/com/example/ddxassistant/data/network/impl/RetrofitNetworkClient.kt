package com.example.ddxassistant.data.network.impl

import com.example.ddxassistant.data.dto.Response
import com.example.ddxassistant.data.dto.WorkoutDateRequest
import com.example.ddxassistant.data.network.AccountApi
import com.example.ddxassistant.data.network.ExercisesAPI
import com.example.ddxassistant.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(private val accountService: AccountApi, private val exerciseService: ExercisesAPI):NetworkClient {
    override suspend fun doRequest(dto: Any): Response {
        if(dto is WorkoutDateRequest){
            return withContext(Dispatchers.IO){
                try{
                    val response = exerciseService.getWorkoutsForDate(dto.date)
                    response.apply { resultCode = 200 }
                } catch(e: Throwable) {
                    Response().apply { resultCode = 500 }
                }
            }
        }else{
            return Response().apply { resultCode = 400 }
        }
    }
}