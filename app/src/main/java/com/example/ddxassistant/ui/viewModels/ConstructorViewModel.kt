package com.example.ddxassistant.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ddxassistant.domain.interfaces.WorkoutInteractor
import com.example.ddxassistant.ui.states.WorkoutScheduleStates
import java.util.Calendar
import java.util.Date

class ConstructorViewModel(private val workoutInteractor: WorkoutInteractor): ViewModel() {
    private var screenStateLiveData = MutableLiveData<WorkoutScheduleStates>(WorkoutScheduleStates.Default)
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
    fun writeToList(){

    }
}