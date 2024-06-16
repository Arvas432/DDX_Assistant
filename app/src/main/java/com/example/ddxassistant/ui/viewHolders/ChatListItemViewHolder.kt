package com.example.ddxassistant.ui.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.ChatListItemBinding
import com.example.ddxassistant.databinding.MessageListItemBinding
import com.example.ddxassistant.domain.model.ChatListItemPojo

class ChatListItemViewHolder(private val binding: ChatListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: ChatListItemPojo){
        binding.chatterNameTv.text = model.chatName
        binding.lastMessageTv.text = model.lastMessage
    }
}