package com.example.ddxassistant.di

import com.example.ddxassistant.ui.AuthViewModel
import com.example.ddxassistant.ui.LoadingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel {
        AuthViewModel(get())
    }
    viewModel {
        LoadingViewModel(get())
    }
}