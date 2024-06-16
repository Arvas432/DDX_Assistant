package com.example.ddxassistant.domain.model

data class Workout (
    var workoutName: String,
    val duration: String,
    var exercises: List<Exercise>
)
