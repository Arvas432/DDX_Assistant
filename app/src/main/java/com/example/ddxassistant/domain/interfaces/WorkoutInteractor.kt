package com.example.ddxassistant.domain.interfaces

import com.example.ddxassistant.domain.model.Exercise
import com.example.ddxassistant.domain.model.ExerciseWrapper
import com.example.ddxassistant.domain.model.ServerRequestType
import com.example.ddxassistant.domain.model.Workout
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface WorkoutInteractor {
    fun getWorkoutsByDate(date: Date): Flow<Pair<List<Workout>, ServerRequestType>>
    fun addExerciseToList(exercise: ExerciseWrapper)
    fun pushExerciseListToServer(date: Date)
    fun addExerciseToList(exercise: Exercise)
    fun getCurrentWorkoutList(): List<Exercise>
}