package com.example.ddxassistant.ui.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.ExerciseCategoryLayoutBinding
import com.example.ddxassistant.domain.model.ExerciseCategoryPojo

class ExerciseCategoryViewHolder(private val binding: ExerciseCategoryLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: ExerciseCategoryPojo){
        binding.categoryIcon.setImageResource(model.categoryIconId)
        binding.categorySubtext.text = model.categorySubtext
        binding.categoryName.text = model.categoryName
        binding.categoryBackground.setImageResource(model.backgroundImageId)
    }
}