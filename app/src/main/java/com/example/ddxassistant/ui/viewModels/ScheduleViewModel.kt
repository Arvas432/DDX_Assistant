package com.example.ddxassistant.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ddxassistant.domain.interfaces.WorkoutInteractor
import com.example.ddxassistant.domain.model.ServerRequestType
import com.example.ddxassistant.ui.states.WorkoutScheduleStates
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Thread.State
import java.util.Calendar
import java.util.Date

class ScheduleViewModel(private val workoutInteractor: WorkoutInteractor): ViewModel() {
    private var screenStateLiveData = MutableLiveData<WorkoutScheduleStates>(WorkoutScheduleStates.Default)
    fun searchWorkoutsForDate(month: Int, day: Int){
        viewModelScope.launch {
            workoutInteractor.getWorkoutsByDate(getDateFromMonthAndDay(month, day)).collect{
                when(it.second){
                    ServerRequestType.SUCCESS ->{
                        renderState(WorkoutScheduleStates.Content(it.first))
                    }
                    ServerRequestType.LOADING -> Unit
                    ServerRequestType.ERROR -> renderState(WorkoutScheduleStates.Empty)
                    ServerRequestType.EMPTY -> renderState(WorkoutScheduleStates.Empty)
                }

            }
        }
    }
    fun getScreenStateLiveDate(): LiveData<WorkoutScheduleStates> = screenStateLiveData
    fun getDateFromMonthAndDay(month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR))
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        Log.i("КАЛЕНДАРЬ",calendar.time.toString())
        return calendar.time
    }
    fun renderState(state: WorkoutScheduleStates){
        screenStateLiveData.postValue(state)
    }
}