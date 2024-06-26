package com.example.ddxassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.ExerciseListItemBinding
import com.example.ddxassistant.domain.model.Exercise
import com.example.ddxassistant.domain.model.ExerciseCategoryPojo
import com.example.ddxassistant.domain.model.ExerciseWrapper
import com.example.ddxassistant.ui.viewHolders.ExerciseCategoryViewHolder
import com.example.ddxassistant.ui.viewHolders.ExerciseViewHolder

class ExerciseListAdapter(private val exercises: List<ExerciseWrapper>, private val action:(Int)->Unit): RecyclerView.Adapter<ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return ExerciseViewHolder(ExerciseListItemBinding.inflate(layoutInspector, parent, false))
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exercises[position]){
            changeCheckedStateOfItem(it,position)
        }
        holder.itemView.setOnClickListener{
            action(position)
        }
    }
    fun changeCheckedStateOfItem(checked: Boolean, position: Int){
        exercises[position].isChecked = checked
    }

}