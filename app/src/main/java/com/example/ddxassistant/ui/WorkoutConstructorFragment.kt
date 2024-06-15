package com.example.ddxassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentWorkoutConstructorBinding


class WorkoutConstructorFragment : BindingFragment<FragmentWorkoutConstructorBinding>(){
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWorkoutConstructorBinding {
        return FragmentWorkoutConstructorBinding.inflate(inflater, container, false)
    }

}