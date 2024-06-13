package com.example.ddxassistant.domain.interfaces

import com.example.ddxassistant.domain.model.Workout
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface WorkoutRepository {
    fun getWorkoutsForDate(date: Date): Flow<Pair<List<Workout>, Boolean>>
}