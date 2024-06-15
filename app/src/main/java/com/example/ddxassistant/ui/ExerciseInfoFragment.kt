package com.example.ddxassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentExerciseInfoBinding
import com.example.ddxassistant.databinding.FragmentTrainingScheduleBinding


class ExerciseInfoFragment : BindingFragment<FragmentExerciseInfoBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExerciseInfoBinding {
        return FragmentExerciseInfoBinding.inflate(inflater, container, false)
    }

}