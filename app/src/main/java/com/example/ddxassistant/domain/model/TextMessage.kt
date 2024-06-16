package com.example.ddxassistant.domain.model

data class TextMessage(
    val author: String,
    var text: String,
    val time: String,
    val isUser: Boolean
)
