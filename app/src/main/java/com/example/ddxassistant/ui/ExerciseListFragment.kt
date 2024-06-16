package com.example.ddxassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentExerciseListBinding
import com.example.ddxassistant.domain.model.Exercise
import com.example.ddxassistant.ui.adapters.ExerciseListAdapter
import com.google.gson.Gson

class ExerciseListFragment : BindingFragment<FragmentExerciseListBinding>() {
    private var sampleExercisesList = mutableListOf<Exercise>(Exercise("Приседания","Ноги", "Ягодицы", "Базовое", "Не требуется", "Начинающий", emptyList()))
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExerciseListBinding {
        return FragmentExerciseListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoriesRv.adapter = ExerciseListAdapter(sampleExercisesList){
            findNavController().navigate(R.id.action_exerciseListFragment_to_exerciseInfoFragment,
                bundleOf(EXERCISE_KEY to Gson().toJson(sampleExercisesList[it]))
            )
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    companion object{
        const val EXERCISE_KEY = "EXERCISE_KEY"
    }

}