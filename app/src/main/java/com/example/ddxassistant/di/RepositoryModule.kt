package com.example.ddxassistant.di

import com.example.ddxassistant.data.impl.AuthRepositoryImpl
import com.example.ddxassistant.data.impl.ChatRepositoryImpl
import com.example.ddxassistant.data.impl.WorkoutRepositoryImpl
import com.example.ddxassistant.domain.interfaces.AuthRepository
import com.example.ddxassistant.domain.interfaces.ChatRepository
import com.example.ddxassistant.domain.interfaces.WorkoutRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single<ChatRepository>{
        ChatRepositoryImpl()
    }
    single<WorkoutRepository>{
        WorkoutRepositoryImpl(get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}