package com.example.ddxassistant.domain.model

import com.example.ddxassistant.data.dto.WorkoutDto

data class Workout (
    val workoutName: String,
    val exercises: List<Exercise>
)
