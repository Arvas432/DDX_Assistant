package com.example.ddxassistant.data.dto

import com.google.gson.annotations.SerializedName

data class WorkoutDto(
    @SerializedName("")
    val workoutName: String,
    val exercises: List<ExerciseDto>
)
