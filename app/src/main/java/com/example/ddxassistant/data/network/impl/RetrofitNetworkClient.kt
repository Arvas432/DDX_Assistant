package com.example.ddxassistant.data.network.impl

import android.util.Log
import com.example.ddxassistant.data.dto.model.ExerciseDto
import com.example.ddxassistant.data.dto.model.WorkoutDto
import com.example.ddxassistant.data.dto.request.AuthRequestEmailPassword
import com.example.ddxassistant.data.dto.request.SignUpRequestEmailPassword
import com.example.ddxassistant.data.dto.response.Response
import com.example.ddxassistant.data.dto.request.WorkoutDateRequest
import com.example.ddxassistant.data.dto.response.WorkoutDateResponse
import com.example.ddxassistant.data.local.TokenSharedPreferencesManager
import com.example.ddxassistant.data.network.AccountApi
import com.example.ddxassistant.data.network.ExercisesAPI
import com.example.ddxassistant.data.network.NetworkClient
import com.example.ddxassistant.ui.adapters.CalendarAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

class RetrofitNetworkClient(
    private val accountService: AccountApi,
    private val exerciseService: ExercisesAPI,
    private val tokenSharedPreferencesManager: TokenSharedPreferencesManager
) : NetworkClient {
    val token = MutableStateFlow("")
    val today : Date = Calendar.getInstance().time
    private var testWorkoutList = mutableListOf<WorkoutDto>(
        WorkoutDto("Тренировка на ноги", "20 минут", listOf(
        ExerciseDto("Вертикальная тяга нижнего бока", "Трапеции", "Спина","Бок","Базовое", "Не требуется", "Начинающий", emptyList()),
        ExerciseDto("Вертикальная тяга верхнего бока", "Трапеции", "Спина","Бок","Базовое", "Не требуется", "Начинающий", emptyList()),
        ExerciseDto("Вертикальная тяга среднего бока", "Трапеции", "Спина","Бок","Базовое", "Не требуется", "Начинающий", emptyList())
    ))
    )
    init {
        token.value = tokenSharedPreferencesManager.getToken()
    }

    override suspend fun doRequest(dto: Any): Response {
        if (dto is WorkoutDateRequest) {
            return withContext(Dispatchers.IO) {
                try {
                    val response = exerciseService.getWorkoutsForDate(dto.date)
                    response.apply { resultCode = 200 }
                } catch (e: Throwable) {
                    //Тестовый код без бэка
                    Log.i("DATE", dto.date.toString())
                    val cal1 = Calendar.getInstance().apply { time = dto.date }
                    val cal2 = Calendar.getInstance().apply { time = today }
                    val date1 = cal1.get(Calendar.DAY_OF_MONTH).toString()
                    val date2 = cal2.get(Calendar.DAY_OF_MONTH).toString()
                    Log.i("DATE", cal2.get(Calendar.DAY_OF_MONTH).toString())
                    Log.i("date1", date1)
                    Log.i("date2", date2)
                    if(date1 == date2){
                        WorkoutDateResponse(testWorkoutList).apply { resultCode=200 }
                    } else{
                        Response().apply { resultCode = 500 }
                    }

                }
            }
        } else if (dto is SignUpRequestEmailPassword) {
            return withContext(Dispatchers.IO) {
                try {
                    val response = accountService.signUp(
                        dto.auth
                    )
                    Log.d("signUp", response.toString())
                    response.apply { resultCode = 200 }

                } catch (e: Exception) {
                    Log.i("network exception", e.toString())
                    Response().apply { resultCode = 500 }
                }
            }
        } else if (dto is AuthRequestEmailPassword) {
            return withContext(Dispatchers.IO) {
                try {
                    val response = accountService.signIn(
                        dto.auth
                    )
                    if (response.auth.error == "") {
                        val receivedToken = response.auth.token.trim()
                        token.value = receivedToken
                        tokenSharedPreferencesManager.saveToken(receivedToken)
                        Log.d("signUp", response.toString())
                        response.apply { resultCode = 200 }
                    } else {
                        Log.i("network exception", response.auth.error)
                        Response().apply { resultCode = 501 }
                    }
                }
                catch (e: Exception) {
                    Log.i("network exception", e.toString())
                    Response().apply { resultCode = 500 }
                }
            }
        }
        else{
            return Response().apply { resultCode = 400 }
        }
    }
}