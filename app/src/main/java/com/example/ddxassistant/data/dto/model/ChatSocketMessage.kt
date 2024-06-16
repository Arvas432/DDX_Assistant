package com.example.ddxassistant.data.dto.model

import java.time.LocalDateTime

data class ChatSocketMessage(
    val text: String,
    val author: String,
    val datetime: LocalDateTime,
    var receiver: String? = null
)
