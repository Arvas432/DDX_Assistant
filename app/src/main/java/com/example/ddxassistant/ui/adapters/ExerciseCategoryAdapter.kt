package com.example.ddxassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.ExerciseCategoryLayoutBinding
import com.example.ddxassistant.domain.model.ExerciseCategoryPojo
import com.example.ddxassistant.ui.viewHolders.ExerciseCategoryViewHolder

class ExerciseCategoryAdapter(private val categories: List<ExerciseCategoryPojo>, private val action:(Int)->Unit): RecyclerView.Adapter<ExerciseCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseCategoryViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return ExerciseCategoryViewHolder(ExerciseCategoryLayoutBinding.inflate(layoutInspector, parent, false))
    }


    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ExerciseCategoryViewHolder, position: Int) {
        holder.bind(categories[position])
        holder.itemView.setOnClickListener{
            action(position)
        }
    }
}