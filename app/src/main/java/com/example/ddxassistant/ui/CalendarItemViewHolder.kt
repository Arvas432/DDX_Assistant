package com.example.ddxassistant.ui

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.CalendarListItemBinding
import com.example.ddxassistant.databinding.MessageListItemBinding
import com.example.ddxassistant.domain.model.CalendarItemPojo

class CalendarItemViewHolder(private val binding: CalendarListItemBinding): RecyclerView.ViewHolder(binding.root)  {
    fun bind(model: CalendarItemPojo){
        binding.dayOfMonth.text = model.date
        binding.dayOfWeek.text = model.dayOfWeek
        binding.hasWorkouts.isVisible = model.hasWorkouts
    }
}