package com.example.ddxassistant.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentRoleChoiceBinding
import com.example.ddxassistant.ui.MainActivity

class RoleChoiceFragment : BindingFragment<FragmentRoleChoiceBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoleChoiceBinding {
        return FragmentRoleChoiceBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showSystemUI()
        binding.clientButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_roleChoiceFragment_to_authFragment, bundleOf(
                ROLE_KEY to false))
        }
        binding.coachButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_roleChoiceFragment_to_authFragment, bundleOf(
                ROLE_KEY to true))
        }
    }
    companion object{
        const val ROLE_KEY = "ROLE_KEY"
    }

}