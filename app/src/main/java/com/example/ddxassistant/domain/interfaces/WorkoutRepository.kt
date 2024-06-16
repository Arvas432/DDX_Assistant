package com.example.ddxassistant.domain.interfaces

import com.example.ddxassistant.domain.model.Exercise
import com.example.ddxassistant.domain.model.Workout
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface WorkoutRepository {
    val exerciseConstructorList: MutableList<Exercise>
    fun getWorkoutsForDate(date: Date): Flow<Pair<List<Workout>, Boolean>>
    fun pushExerciseListToServer(date: Date)
    fun addExerciseToList(exercise: Exercise)
    fun getCurrentWorkoutList(): List<Exercise>
}