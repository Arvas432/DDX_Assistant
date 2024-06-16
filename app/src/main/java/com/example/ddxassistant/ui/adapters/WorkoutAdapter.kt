package com.example.ddxassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.WorkoutListItemBinding
import com.example.ddxassistant.domain.model.Workout
import com.example.ddxassistant.ui.viewHolders.WorkoutItemViewHolder

class WorkoutAdapter(private val workouts: List<Workout>, private val action: (Int) -> Unit): RecyclerView.Adapter<WorkoutItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutItemViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return WorkoutItemViewHolder(WorkoutListItemBinding.inflate(layoutInspector, parent, false))
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    override fun onBindViewHolder(holder: WorkoutItemViewHolder, position: Int) {
        holder.bind(workouts[position])
        holder.itemView.setOnClickListener{
            action(position)
        }
    }
}