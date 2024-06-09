package com.example.ddxassistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.databinding.FragmentRoleChoiceBinding

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
            findNavController().navigate(R.id.action_roleChoiceFragment_to_authFragment, bundleOf(
                ROLE_KEY to false))
        }
        binding.coachButton.setOnClickListener {
            findNavController().navigate(R.id.action_roleChoiceFragment_to_authFragment, bundleOf(
                ROLE_KEY to true))
        }
    }
    companion object{
        const val ROLE_KEY = "ROLE_KEY"
    }

}