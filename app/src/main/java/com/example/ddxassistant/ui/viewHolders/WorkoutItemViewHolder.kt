package com.example.ddxassistant.ui.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.WorkoutListItemBinding
import com.example.ddxassistant.domain.model.Workout

class WorkoutItemViewHolder(private val binding: WorkoutListItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Workout){
        binding.trainingName.text = model.workoutName
        binding.exerciseCounter.text = model.exercises.count().toString()
        binding.workoutTime.text = model.duration
    }
}