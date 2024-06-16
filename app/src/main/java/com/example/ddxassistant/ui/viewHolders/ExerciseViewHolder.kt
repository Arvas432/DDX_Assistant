package com.example.ddxassistant.ui.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.ExerciseListItemBinding
import com.example.ddxassistant.databinding.WorkoutListItemBinding
import com.example.ddxassistant.domain.model.Exercise
import com.example.ddxassistant.domain.model.ExerciseWrapper

class ExerciseViewHolder(private val binding: ExerciseListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: ExerciseWrapper){
        binding.exerciseTitle.text = model.exercise.name
        binding.checkbox.isChecked = model.isChecked
        binding.exerciseCountEt.setText(model.count.toString())
        binding.setCountEt.setText(model.setCount.toString())
    }
}