package com.example.ddxassistant.data.dto.model

import com.example.ddxassistant.data.dto.model.ExerciseDto
import com.google.gson.annotations.SerializedName

data class WorkoutDto(
    @SerializedName("")
    val workoutName: String,
    val exercises: List<ExerciseDto>
)
