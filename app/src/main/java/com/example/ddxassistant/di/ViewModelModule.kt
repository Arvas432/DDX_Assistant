package com.example.ddxassistant.di

import com.example.ddxassistant.ui.viewModels.AuthViewModel
import com.example.ddxassistant.ui.viewModels.ConstructorViewModel
import com.example.ddxassistant.ui.viewModels.LoadingViewModel
import com.example.ddxassistant.ui.viewModels.ScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel {
        AuthViewModel(get())
    }
    viewModel {
        LoadingViewModel(get())
    }
    viewModel{
        ScheduleViewModel(get())
    }
    viewModel{
        ConstructorViewModel(get())
    }
}