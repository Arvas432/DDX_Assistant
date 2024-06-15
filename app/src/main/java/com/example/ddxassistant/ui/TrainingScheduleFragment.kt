package com.example.ddxassistant.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.databinding.FragmentTrainingScheduleBinding
import com.example.ddxassistant.domain.model.CalendarItemPojo
import com.example.ddxassistant.domain.model.Workout
import java.text.SimpleDateFormat
import java.util.Calendar

class TrainingScheduleFragment : BindingFragment<FragmentTrainingScheduleBinding>() {
    private var currentWeekList = mutableListOf<CalendarItemPojo>()
    private lateinit var selectedMonth: String
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTrainingScheduleBinding {
        return FragmentTrainingScheduleBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillWeekDaysList()
        Log.i("КАЛЕНДАРЬ", currentWeekList.toString())
        val testWorkoutList = mutableListOf<Workout>(Workout("Тренировка 1", "7 лет", emptyList()))
        val workoutAdapter = WorkoutAdapter(testWorkoutList)
        binding.trainingRv.adapter = workoutAdapter
        binding.doneCounterTv.text = "0"
        binding.inProgressCounterTv.text = "0"
        binding.missedCounterTv.text = "0"
        val monthName = arrayOf(
            "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь",
            "Декабрь"
        )

        binding.dateTv.text = monthName[Calendar.MONTH]
        selectedMonth = monthName[Calendar.MONTH]
        Log.i("Month",Calendar.MONTH.toString())
        val calendarAdapter = CalendarAdapter(currentWeekList, getToday())
        binding.calendarLayout.adapter = calendarAdapter
        if (getToday().toInt()>4){
            binding.calendarLayout.scrollToPosition((getToday().toInt() - 4))
        }

    }
    private fun fillWeekDaysList(){
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd")
        val daysOfWeek = arrayListOf<String>("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
        val currentMonth = calendar.get(Calendar.MONTH)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        while (calendar.get(Calendar.MONTH) == currentMonth) {
            val dayOfWeek = daysOfWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1]
            currentWeekList.add(CalendarItemPojo(dayOfWeek, dateFormat.format(calendar.time), false))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
    }
    private fun getToday(): String{
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd")
        Log.i("TODAY", dateFormat.format(calendar.time))
        return dateFormat.format(calendar.time)

    }

}