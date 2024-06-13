package com.example.ddxassistant.di

import com.example.ddxassistant.ui.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel {
        AuthViewModel(get())
    }
}