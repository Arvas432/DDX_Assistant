package com.example.ddxassistant.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentWorkoutConstructorBinding
import com.example.ddxassistant.domain.model.ExerciseWrapper
import com.example.ddxassistant.domain.model.Workout
import com.example.ddxassistant.ui.adapters.ExerciseListAdapter
import com.example.ddxassistant.ui.viewModels.ConstructorViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel


class WorkoutConstructorFragment : BindingFragment<FragmentWorkoutConstructorBinding>(){
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWorkoutConstructorBinding {
        return FragmentWorkoutConstructorBinding.inflate(inflater, container, false)
    }
    private val viewModel by viewModel<ConstructorViewModel>()
    private lateinit var workout: Workout
    private var indexOfWorkout = -1
    private var editing = false
    private val exercisesList = mutableListOf<ExerciseWrapper>()
    private lateinit var adapter: ExerciseListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ExerciseListAdapter(exercisesList){
            findNavController().navigate(R.id.action_workoutConstructorFragment_to_exerciseInfoFragment,
                bundleOf(ExerciseListFragment.EXERCISE_KEY to Gson().toJson(exercisesList[it].exercise))
            )
        }
        binding.workoutExerciseListRv.adapter = adapter
        if (arguments!=null){
            val date = requireArguments().getString(DATE_KEY, "")
            if (requireArguments().getString(WORKOUT_KEY)!=null){
                workout = Gson().fromJson<Workout>((requireArguments().getString(WORKOUT_KEY)), Workout::class.java)
                indexOfWorkout = requireArguments().getInt(EDITING_INDEX_KEY)
                editing = true
                binding.workoutNameEt.setText(workout.workoutName)
                if (workout.exercises.isNotEmpty()) {
                    Log.i("упражнения", workout.exercises.toString())
                    val exercisesToRender = workout.exercises.map {  ExerciseWrapper(it, 0, 0, true)  }
                    Log.i("мапленные упражнения", exercisesToRender.toString())
                    renderData(exercisesToRender)
                } else{
                    renderEmpty()
                }
            } else{
                editing = false
                indexOfWorkout = -1
                workout = Workout("", "", emptyList())
            }

        }
        binding.addExerciseButtonSmall.setOnClickListener {
            findNavController().navigate(R.id.action_workoutConstructorFragment_to_exerciseCategoriesFragment)
        }
        binding.addExerciseButton.setOnClickListener {
            findNavController().navigate(R.id.action_workoutConstructorFragment_to_exerciseCategoriesFragment)
        }
        binding.backBtn.setOnClickListener {
            val filteredExercises = exercisesList.filter { it.isChecked }
            for (exercise in filteredExercises){
                viewModel.writeToList(exercise.exercise)
            }
            workout.workoutName = binding.workoutNameEt.text.toString()
            workout.exercises = filteredExercises.map { it.exercise }
            findNavController().navigate(R.id.action_workoutConstructorFragment_to_trainingScheduleFragment, bundleOf(
                WORKOUT_KEY to Gson().toJson(workout),
                EDITING_INDEX_KEY to indexOfWorkout,
                EDITING_KEY to editing))
        }
    }
    private fun renderEmpty(){
        binding.workoutExerciseListRv.isVisible = false
        binding.addExerciseButtonSmall.isVisible = true
        binding.addExerciseButton.isVisible = false
    }
    private fun renderData(data: List<ExerciseWrapper>){
        exercisesList.clear()
        binding.workoutExerciseListRv.isVisible = true
        binding.addExerciseButtonSmall.isVisible = false
        binding.addExerciseButton.isVisible = true
        exercisesList.addAll(data)
        Log.i("Список", exercisesList.toString())
        adapter.notifyDataSetChanged()

    }

    companion object{
        const val WORKOUT_KEY = "WORKOUT_KEY"
        const val EDITING_KEY = "EDITING_KEY"
        const val EDITING_INDEX_KEY = "EDITING_INDEX_KEY"
        const val DATE_KEY ="DATE_KEY"
    }
}