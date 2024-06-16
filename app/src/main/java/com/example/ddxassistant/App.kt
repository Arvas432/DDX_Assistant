package com.example.ddxassistant

import android.app.Application
import android.view.WindowManager
import com.example.ddxassistant.di.RepositoryModule
import com.example.ddxassistant.di.ViewModelModule
import com.example.ddxassistant.di.dataModule
import com.example.ddxassistant.di.interactorModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(dataModule, RepositoryModule, ViewModelModule, interactorModule)
        }
    }
}