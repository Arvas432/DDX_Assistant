package com.example.ddxassistant.domain

data class TextMessage(
    val author: String,
    val text: String,
    val time: String,
    val isUser: Boolean
)
