package com.example.ddxassistant.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentTrainingScheduleBinding
import com.example.ddxassistant.domain.model.CalendarItemPojo
import com.example.ddxassistant.domain.model.Workout
import com.example.ddxassistant.ui.adapters.CalendarAdapter
import com.example.ddxassistant.ui.adapters.WorkoutAdapter
import java.text.SimpleDateFormat
import java.util.Calendar

class TrainingScheduleFragment : BindingFragment<FragmentTrainingScheduleBinding>() {
    private var currentWeekList = mutableListOf<CalendarItemPojo>()
    private lateinit var calendarAdapter: CalendarAdapter
    private var currentMonth: Int = 0
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTrainingScheduleBinding {
        return FragmentTrainingScheduleBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("КАЛЕНДАРЬ", currentWeekList.toString())
        val testWorkoutList = mutableListOf<Workout>(Workout("Тренировка 1", "7 лет", emptyList()))
        val workoutAdapter = WorkoutAdapter(testWorkoutList)
        val dropDownAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.months_array, R.layout.month_spinner_layout).also {
            arrayAdapter ->  arrayAdapter.setDropDownViewResource(R.layout.month_spinner_layout)
        }
        binding.dateTv.adapter = dropDownAdapter
        binding.trainingRv.adapter = workoutAdapter
        binding.doneCounterTv.text = "0"
        binding.inProgressCounterTv.text = "0"
        binding.missedCounterTv.text = "0"
        val calendar = Calendar.getInstance()
        binding.dateTv.setSelection(calendar.get(Calendar.MONTH))
        fillWeekDaysList(calendar.get(Calendar.MONTH))
        Log.i("Month",Calendar.MONTH.toString())
        calendarAdapter = CalendarAdapter(currentWeekList, getToday()){
            val currentDay = calendarAdapter.getSelectedDay()
            //запросНаТренировкиНаДень(currentDay, currentMonth)
        }
        binding.calendarLayout.adapter = calendarAdapter
        if (getToday().toInt()>4){
            binding.calendarLayout.scrollToPosition((getToday().toInt() - 4))
        }
        val addWorkoutOnClickListener = OnClickListener {
            findNavController().navigate(R.id.action_trainingScheduleFragment_to_workoutConstructorFragment,
                bundleOf(
                    DATE_KEY to calendarAdapter.getSelectedDay().toString() + currentMonth ))
        }
        binding.addTrainingButton.setOnClickListener(addWorkoutOnClickListener)
        binding.addTrainingButtonSmall.setOnClickListener(addWorkoutOnClickListener)
        binding.dateTv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                fillWeekDaysList(position)
                calendarAdapter.notifyDataSetChanged()
                currentMonth = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

        }
//        findNavController().navigate(R.id.action_trainingScheduleFragment_to_exerciseCategoriesFragment)

    }
    private fun fillWeekDaysList(month: Int){
        currentWeekList.clear()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd")
        val daysOfWeek = arrayListOf<String>("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        while (calendar.get(Calendar.MONTH) == month) {
            val dayOfWeek = daysOfWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1]
            currentWeekList.add(CalendarItemPojo(dayOfWeek, dateFormat.format(calendar.time), false))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
    }
    private fun renderEmptyWorkoutList(){
        binding.emptyPlaceholderLayout.isVisible = true
        binding.trainingRv.isVisible = false
    }
    private fun renderDefault(){
        binding.emptyPlaceholderLayout.isVisible = false
        binding.trainingRv.isVisible = true
    }
    private fun getToday(): String{
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd")
        Log.i("TODAY", dateFormat.format(calendar.time))
        return dateFormat.format(calendar.time)

    }
    companion object{
        const val DATE_KEY ="DATE_KEY"
    }

}