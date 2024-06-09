package com.example.ddxassistant

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.databinding.FragmentWelcomeBinding

class WelcomeFragment : BindingFragment<FragmentWelcomeBinding>(){
    private val handler = Handler(Looper.getMainLooper())
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWelcomeBinding {
        return FragmentWelcomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler.postDelayed({findNavController().navigate(R.id.action_welcomeFragment_to_featuresFragment) }, 3000L)
    }


}