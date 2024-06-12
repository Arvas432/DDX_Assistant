package com.example.ddxassistant.domain

import com.example.ddxassistant.domain.model.ServerRequestType
import com.example.ddxassistant.domain.model.Workout
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface WorkoutInteractor {
    fun getWorkoutsByDate(date: Date): Flow<Pair<List<Workout>, ServerRequestType>>
}