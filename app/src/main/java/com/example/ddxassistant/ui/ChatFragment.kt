package com.example.ddxassistant.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.databinding.FragmentChatBinding
import com.example.ddxassistant.domain.model.TextMessage
import com.example.ddxassistant.ui.adapters.ChatAdapter
import org.w3c.dom.Text


class ChatFragment : BindingFragment<FragmentChatBinding>(){
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatBinding {
        return FragmentChatBinding.inflate(inflater, container, false)
    }
    private var sampleMessages = mutableListOf<TextMessage>()
    private lateinit var adapter: ChatAdapter
    private var currentMessage = TextMessage("User", "", "8:00AM", true)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sampleMessages = mutableListOf(
            TextMessage(author = "User", text = "Доброе утро", time = "08:00 AM", isUser = false),
            TextMessage(author = "User", text = "Как продвигаются тренировки?", time = "08:01 AM", isUser = false),
            TextMessage(author = "User", text = "Сделать вам подходов побольше?", time = "08:02 AM", isUser = false),
            TextMessage(author = "Friend", text = "Я с трудом пережила три подхода подряд на выпады, но уже готова к новым испытаниям\n\nДобавляйте!", time = "08:03 AM", isUser = true),
            TextMessage(author = "User", text = "Хмммм\n\nЯ вас понял", time = "08:04 AM", isUser = false),
            TextMessage(author = "User", text = "Тогда завтра уменьшу немного выпады и добавлю на руки", time = "08:05 AM", isUser = false)
        )
        adapter = ChatAdapter(sampleMessages)
        binding.chatRv.adapter = adapter
        val textFieldTextWatcher = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentMessage.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        binding.textMessageEt.setOnEditorActionListener { v, actionId, event ->
            writeMessage(currentMessage)
            false
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    fun writeMessage(textMessage: TextMessage){
        sampleMessages.add(textMessage)
        adapter.notifyItemInserted(adapter.itemCount-1)
    }

}