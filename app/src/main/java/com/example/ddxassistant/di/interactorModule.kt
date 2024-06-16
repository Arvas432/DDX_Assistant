package com.example.ddxassistant.di

import com.example.ddxassistant.domain.impl.AuthInteractorImpl
import com.example.ddxassistant.domain.interfaces.ChatInteractor
import com.example.ddxassistant.domain.interfaces.WorkoutInteractor
import com.example.ddxassistant.domain.impl.ChatInteractorImpl
import com.example.ddxassistant.domain.impl.WorkoutInteractorImpl
import com.example.ddxassistant.domain.interfaces.AuthInteractor
import org.koin.dsl.module

val interactorModule = module{
    factory<ChatInteractor> {
        ChatInteractorImpl()
    }
    factory<WorkoutInteractor> {
        WorkoutInteractorImpl(get())
    }
    factory<AuthInteractor> {
        AuthInteractorImpl(get())
    }
}