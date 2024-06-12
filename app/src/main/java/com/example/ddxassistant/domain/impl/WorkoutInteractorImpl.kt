package com.example.ddxassistant.domain.impl

import com.example.ddxassistant.domain.WorkoutInteractor
import com.example.ddxassistant.domain.WorkoutRepository
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
}