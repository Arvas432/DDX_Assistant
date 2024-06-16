package com.example.ddxassistant.data.impl

import android.util.Log
import com.example.ddxassistant.data.dto.request.WorkoutDateRequest
import com.example.ddxassistant.data.dto.response.WorkoutDateResponse
import com.example.ddxassistant.data.network.NetworkClient
import com.example.ddxassistant.domain.interfaces.WorkoutRepository
import com.example.ddxassistant.domain.model.Exercise
import com.example.ddxassistant.domain.model.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

class WorkoutRepositoryImpl(private val networkClient: NetworkClient
): WorkoutRepository {
    override val exerciseConstructorList: MutableList<Exercise> = mutableListOf()
    override fun getWorkoutsForDate(date: Date): Flow<Pair<List<Workout>, Boolean>> = flow{
        val response = networkClient.doRequest(WorkoutDateRequest(date))
        when(response.resultCode){
            200 ->{
                with(response as WorkoutDateResponse){
                    val data = results.map {
                        Workout(
                            workoutName = it.workoutName,
                            it.workoutTime,
                            exercises = it.exercises.map { Exercise(
                                it.name ?:"",
                                it.difficulty ?:"",
                                it.additionalMuscle ?:"",
                                it.type ?:"",
                                it.equipment ?:"",
                                it.difficulty ?:"",
                                it.photos,) }
                        )
                    }
                    emit(Pair(data, true))
                }
            } else -> {
                emit(Pair(emptyList(), false))
            }
        }
    }

    override fun pushExerciseListToServer(date: Date) {
        Log.i("НЕ РЕАЛИЗОВАНО", "отправка на сервер")
    }

    override fun addExerciseToList(exercise: Exercise) {
        exerciseConstructorList.add(exercise)
    }

    override fun getCurrentWorkoutList(): List<Exercise> {
        return exerciseConstructorList
    }
}