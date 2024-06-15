package com.example.ddxassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentExerciseListBinding

class ExerciseListFragment : BindingFragment<FragmentExerciseListBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExerciseListBinding {
        return FragmentExerciseListBinding.inflate(inflater, container, false)
    }

}