package com.example.ddxassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentChatBinding
import com.example.ddxassistant.domain.TextMessage


class ChatFragment : BindingFragment<FragmentChatBinding>(){
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatBinding {
        return FragmentChatBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val messages = listOf(
            TextMessage(author = "Alice", text = "Hey, how are you?", time = "10:00 AM", isUser = true),
            TextMessage(author = "Bob", text = "I'm good, thanks! What about you?", time = "10:05 AM", isUser = false),
            TextMessage(author = "Alice", text = "I'm doing well too. What are your plans for today?", time = "10:10 AM", isUser = true),
            TextMessage(author = "Bob", text = "Just some work and then maybe a movie. You?", time = "10:15 AM", isUser = false),
            TextMessage(author = "Alice", text = "Sounds good. I'm thinking of going for a walk later.", time = "10:20 AM", isUser = true),
            TextMessage(author = "Charlie", text = "Hey everyone, what's up?", time = "10:25 AM", isUser = false),
            TextMessage(author = "Alice", text = "Hi Charlie, just chatting with Bob. How about you?", time = "10:30 AM", isUser = true),
            TextMessage(author = "Charlie", text = "Just finished breakfast. Any plans for the weekend?", time = "10:35 AM", isUser = false),
            TextMessage(author = "Bob", text = "Thinking of going hiking. You?", time = "10:40 AM", isUser = false),
            TextMessage(author = "Charlie", text = "Might visit my grandparents.", time = "10:45 AM", isUser = false)
        )
        val adapter = ChatAdapter(messages)
        binding.chatRv.adapter = adapter
    }

}