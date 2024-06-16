package com.example.ddxassistant.ui.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class LoadingViewModel(private val sharedPreferences: SharedPreferences): ViewModel() {
    fun checkFirstLaunch(): Boolean{
        val firstLaunchToken = sharedPreferences.getBoolean(firstLaunchKey, true)
        if (firstLaunchToken){
            sharedPreferences.edit().putBoolean(firstLaunchKey, false).apply()
        }
        return firstLaunchToken
    }
    companion object{
        const val firstLaunchKey = "firstlaunchkey"
    }
}