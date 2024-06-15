package com.example.ddxassistant.di

import android.content.Context
import android.content.SharedPreferences
import com.example.ddxassistant.data.local.TokenSharedPreferencesManager
import com.example.ddxassistant.data.network.AccountApi
import com.example.ddxassistant.data.network.AuthInterceptor
import com.example.ddxassistant.data.network.ExercisesAPI
import com.example.ddxassistant.data.network.NetworkClient
import com.example.ddxassistant.data.network.impl.RetrofitNetworkClient
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val dataModule = module {
    val apiBaseUrl = "0.0.0.0"
    val sharedPreferencesToken = "token"
    single<AccountApi>{
        val authInterceptor = AuthInterceptor(get())
        val client = OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        Retrofit
            .Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiBaseUrl)
            .build()
            .create(AccountApi::class.java)

    }
    single<ExercisesAPI>{
        Retrofit
            .Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExercisesAPI::class.java)
    }
    single<NetworkClient>{
        RetrofitNetworkClient(get(), get(), get())
    }
    single<Gson>{
        Gson()
    }
    single<TokenSharedPreferencesManager>{
        TokenSharedPreferencesManager(get())
    }
    single<SharedPreferences>{
        androidContext().getSharedPreferences(sharedPreferencesToken, Context.MODE_PRIVATE)
    }
}