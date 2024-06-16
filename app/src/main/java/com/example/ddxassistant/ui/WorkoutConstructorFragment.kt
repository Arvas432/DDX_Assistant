package com.example.ddxassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentWorkoutConstructorBinding


class WorkoutConstructorFragment : BindingFragment<FragmentWorkoutConstructorBinding>(){
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWorkoutConstructorBinding {
        return FragmentWorkoutConstructorBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = requireArguments().getString(DATE_KEY, "")
        binding.addExerciseButtonSmall.setOnClickListener {
            findNavController().navigate(R.id.action_workoutConstructorFragment_to_exerciseCategoriesFragment)
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object{
        const val DATE_KEY ="DATE_KEY"
    }
}