package com.example.ddxassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.MessageListItemBinding
import com.example.ddxassistant.domain.model.TextMessage
import com.example.ddxassistant.ui.viewHolders.ChatMessageViewHolder

class ChatAdapter(private val messages:List<TextMessage>): RecyclerView.Adapter<ChatMessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return ChatMessageViewHolder(MessageListItemBinding.inflate(layoutInspector, parent, false))
    }
    override fun getItemCount(): Int {
        return messages.size
    }
    override fun onBindViewHolder(holder: ChatMessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

}