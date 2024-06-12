package com.example.ddxassistant.domain.model

data class Exercise( val name: String,
                     val muscle: String,
                     val additionalMuscle: String,
                     val type: String,
                     val equipment: String,
                     val difficulty: String,
                     val photosLinks: List<String>)
