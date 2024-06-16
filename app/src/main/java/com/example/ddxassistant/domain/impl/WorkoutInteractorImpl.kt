package com.example.ddxassistant.domain.impl

import com.example.ddxassistant.domain.interfaces.WorkoutInteractor
import com.example.ddxassistant.domain.interfaces.WorkoutRepository
import com.example.ddxassistant.domain.model.Exercise
import com.example.ddxassistant.domain.model.ExerciseWrapper
import com.example.ddxassistant.domain.model.ServerRequestType
import com.example.ddxassistant.domain.model.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class WorkoutInteractorImpl(private val repository: WorkoutRepository): WorkoutInteractor {
    override fun getWorkoutsByDate(date: Date): Flow<Pair<List<Workout>, ServerRequestType>> {
        return repository.getWorkoutsForDate(date).map { result ->
            if(result.second){
                if (result.first.isEmpty()){
                    Pair(result.first, ServerRequestType.EMPTY)
                }
                else{
                    Pair(result.first, ServerRequestType.SUCCESS)
                }
            }else{
                Pair(emptyList(), ServerRequestType.ERROR)
            }
        }
    }

    override fun addExerciseToList(exercise: ExerciseWrapper) {
        repository.addExerciseToList(exercise.exercise)
    }

    override fun addExerciseToList(exercise: Exercise) {
        repository.addExerciseToList(exercise)
    }

    override fun pushExerciseListToServer(date: Date) {
        repository.pushExerciseListToServer(date)
    }

    override fun getCurrentWorkoutList(): List<Exercise> {
        return repository.getCurrentWorkoutList()
    }

}