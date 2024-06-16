package com.example.ddxassistant.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentWorkoutConstructorBinding
import com.example.ddxassistant.domain.model.ExerciseWrapper
import com.example.ddxassistant.domain.model.Workout
import com.google.gson.Gson


class WorkoutConstructorFragment : BindingFragment<FragmentWorkoutConstructorBinding>(){
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWorkoutConstructorBinding {
        return FragmentWorkoutConstructorBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments!=null){
            val date = requireArguments().getString(DATE_KEY, "")
            val workout = Gson().fromJson<Workout>((requireArguments().getString(WORKOUT_KEY)), Workout::class.java)
            if (workout.exercises.isNotEmpty()){
                renderData(workout.exercises.map { ExerciseWrapper(it, 0, 0, true) })
            }
        }
        binding.addExerciseButtonSmall.setOnClickListener {
            findNavController().navigate(R.id.action_workoutConstructorFragment_to_exerciseCategoriesFragment)
        }
        binding.addExerciseButton.setOnClickListener {
            findNavController().navigate(R.id.action_workoutConstructorFragment_to_exerciseCategoriesFragment)
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun renderEmpty(){
        binding.workoutExerciseListRv.isVisible = false
        binding.addExerciseButtonSmall.isVisible = true
        binding.addExerciseButton.isVisible = false
    }
    private fun renderData(data: List<ExerciseWrapper>){
        binding.workoutExerciseListRv.isVisible = true
        binding.addExerciseButtonSmall.isVisible = false
        binding.addExerciseButton.isVisible = true

    }

    companion object{
        const val WORKOUT_KEY = "WORKOUT_KEY"
        const val DATE_KEY ="DATE_KEY"
    }
}