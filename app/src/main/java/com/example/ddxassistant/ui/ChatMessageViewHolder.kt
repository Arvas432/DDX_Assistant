package com.example.ddxassistant.ui

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.MessageListItemBinding
import com.example.ddxassistant.domain.TextMessage

class ChatMessageViewHolder(private val binding: MessageListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: TextMessage){
        when(model.isUser){
            true -> {
                binding.userMessageLayout.isVisible = true
                binding.author.text = model.author
                binding.messageText.text = model.text
                binding.datetime.text = model.time
            }
            false -> {
                binding.coachMessageLayout.isVisible = true
                binding.authorCoach.text = model.author
                binding.messageTextCoach.text = model.text
                binding.datetimeCoach.text = model.time
            }
        }
    }
}