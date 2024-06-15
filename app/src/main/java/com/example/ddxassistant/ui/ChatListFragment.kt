package com.example.ddxassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentChatListBinding


class ChatListFragment : BindingFragment<FragmentChatListBinding>(){
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatListBinding {
        return FragmentChatListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}