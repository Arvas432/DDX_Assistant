package com.example.ddxassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentChatListBinding
import com.example.ddxassistant.domain.model.ChatListItemPojo
import com.example.ddxassistant.ui.adapters.ChatListAdapter


class ChatListFragment : BindingFragment<FragmentChatListBinding>(){
    private val sampleChatList = mutableListOf<ChatListItemPojo>(ChatListItemPojo("Тренер Алексей", "Все круто"))
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatListBinding {
        return FragmentChatListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoriesRv.adapter = ChatListAdapter(sampleChatList){
            findNavController().navigate(R.id.action_chatListFragment_to_chatFragment)
        }
    }


}