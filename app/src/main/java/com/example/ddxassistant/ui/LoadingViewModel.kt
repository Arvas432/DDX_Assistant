package com.example.ddxassistant.ui

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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