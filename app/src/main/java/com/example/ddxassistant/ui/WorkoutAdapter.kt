package com.example.ddxassistant.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.WorkoutListItemBinding
import com.example.ddxassistant.domain.model.Workout

class WorkoutAdapter(private val workouts: List<Workout>): RecyclerView.Adapter<WorkoutItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutItemViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return WorkoutItemViewHolder(WorkoutListItemBinding.inflate(layoutInspector, parent, false))
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    override fun onBindViewHolder(holder: WorkoutItemViewHolder, position: Int) {
        holder.bind(workouts[position])
    }
}