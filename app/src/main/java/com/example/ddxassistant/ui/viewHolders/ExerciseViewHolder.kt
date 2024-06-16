package com.example.ddxassistant.ui.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.ExerciseListItemBinding
import com.example.ddxassistant.databinding.WorkoutListItemBinding
import com.example.ddxassistant.domain.model.Exercise

class ExerciseViewHolder(private val binding: ExerciseListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Exercise){
        binding.exerciseTitle.text = model.name
    }
}