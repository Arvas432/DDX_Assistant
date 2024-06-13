package com.example.ddxassistant.data.dto.model

import com.google.gson.annotations.SerializedName

data class ExerciseDto(
    @SerializedName("originalUri"      ) var originalUri      : String?           = null,
    @SerializedName("name"             ) var name             : String?           = null,
    @SerializedName("muscle"           ) var muscle           : String?           = null,
    @SerializedName("additionalMuscle" ) var additionalMuscle : String?           = null,
    @SerializedName("type"             ) var type             : String?           = null,
    @SerializedName("equipment"        ) var equipment        : String?           = null,
    @SerializedName("difficulty"       ) var difficulty       : String?           = null,
    @SerializedName("photos"           ) var photos           : ArrayList<String> = arrayListOf()

)
