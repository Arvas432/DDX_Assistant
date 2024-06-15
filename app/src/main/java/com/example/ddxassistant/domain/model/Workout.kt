package com.example.ddxassistant.domain.model

data class Workout (
    val workoutName: String,
    val duration: String,
    val exercises: List<Exercise>
)
