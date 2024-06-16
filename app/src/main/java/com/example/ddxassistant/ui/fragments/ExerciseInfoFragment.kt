package com.example.ddxassistant.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.databinding.FragmentExerciseInfoBinding
import com.example.ddxassistant.domain.model.Exercise
import com.google.gson.Gson


class ExerciseInfoFragment : BindingFragment<FragmentExerciseInfoBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExerciseInfoBinding {
        return FragmentExerciseInfoBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val exercise = Gson().fromJson(requireArguments().getString(EXERCISE_KEY), Exercise::class.java)
        binding.exerciseName.text = exercise.name
//        Glide.with(this)
//            .load(exercise.photosLinks[0]?:"")
//            .centerCrop()
//            .transform(RoundedCorners(30))
//            .into(binding.exerciseImage)
        binding.typeTv.text = exercise.type
        binding.difficultyTv.text = exercise.difficulty
        binding.equipmentTv.text = exercise.difficulty
        binding.muscleTv.text = exercise.muscle
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    companion object{
        const val EXERCISE_KEY = "EXERCISE_KEY"
    }

}