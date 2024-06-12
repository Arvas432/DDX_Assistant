package com.example.ddxassistant.data.network

import com.example.ddxassistant.data.dto.WorkoutDateResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.Date

interface ExercisesAPI {
    @POST
    suspend fun postTraining()
    @GET
    suspend fun getTrainingData()
    @GET
    suspend fun getWorkoutsForDate(@Path("date") date: Date): WorkoutDateResponse
}