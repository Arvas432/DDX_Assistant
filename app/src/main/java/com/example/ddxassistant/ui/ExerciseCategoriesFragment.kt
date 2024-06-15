package com.example.ddxassistant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentExerciseCategoriesBinding
import com.example.ddxassistant.domain.model.ExerciseCategoryPojo
import com.example.ddxassistant.ui.adapters.ExerciseCategoryAdapter


class ExerciseCategoriesFragment : BindingFragment<FragmentExerciseCategoriesBinding>() {
    private var categoriesList = mutableListOf<ExerciseCategoryPojo>(
        ExerciseCategoryPojo(
            "Спина", "Силовые тренировки для верхней части \nтела с акцентом на мышцы спины", R.drawable.back_icon, R.drawable.back),
        ExerciseCategoryPojo(
            "Ягодицы", "Тренировки с акцентом на ягодицы ", R.drawable.butt_icon, R.drawable.butt),
        ExerciseCategoryPojo(
            "Руки", "Силовые тренировки,которые\n помогутпривести руки в форму", R.drawable.bicep_icon, R.drawable.bicep),
        ExerciseCategoryPojo(
            "Пресс", "Тренировки для достижения\nидеального пресса", R.drawable.abs_icon, R.drawable.abs),
        ExerciseCategoryPojo(
            "Ноги", "Силовые тренировки для нижней \nчасти тела с акцентом на мышцы ног", R.drawable.leg_icon, R.drawable.legs),
        ExerciseCategoryPojo(
            "Грудь", "Тренировки с акцентом на \nгрудную клетку", R.drawable.chest_icon, R.drawable.chest),
        ExerciseCategoryPojo(
            "Смешанные\nтренировки", "Функциональные тренировки на\nразличные части тела", R.drawable.mixed_icon, R.color.black
        )
    )
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExerciseCategoriesBinding {
        return FragmentExerciseCategoriesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ExerciseCategoryAdapter(categoriesList)
        binding.categoriesRv.adapter = adapter

    }

}