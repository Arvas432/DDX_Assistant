package com.example.ddxassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.ChatListItemBinding
import com.example.ddxassistant.databinding.ExerciseCategoryLayoutBinding
import com.example.ddxassistant.domain.model.ChatListItemPojo
import com.example.ddxassistant.domain.model.ExerciseCategoryPojo
import com.example.ddxassistant.ui.viewHolders.ChatListItemViewHolder
import com.example.ddxassistant.ui.viewHolders.ExerciseCategoryViewHolder

class ChatListAdapter(private val chats: List<ChatListItemPojo>, private val action:(Int)->Unit): RecyclerView.Adapter<ChatListItemViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListItemViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return ChatListItemViewHolder(ChatListItemBinding.inflate(layoutInspector, parent, false)) }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(holder: ChatListItemViewHolder, position: Int) {
        holder.bind(chats[position])
        holder.itemView.setOnClickListener{
            action(position)
        }
    }
}