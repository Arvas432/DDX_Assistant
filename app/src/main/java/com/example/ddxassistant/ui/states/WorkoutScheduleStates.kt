package com.example.ddxassistant.ui.states

import com.example.ddxassistant.domain.model.Workout

sealed class WorkoutScheduleStates {
    object Default: WorkoutScheduleStates()
    object Empty: WorkoutScheduleStates()
    data class Content(val workouts: List<Workout>): WorkoutScheduleStates()
}