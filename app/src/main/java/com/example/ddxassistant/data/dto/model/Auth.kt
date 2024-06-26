package com.example.ddxassistant.data.dto.model

data class Auth( val name : String  = "",
                 val email: String = "",
                 val password: String = "",
                 val token: String ="",
                 val role: String = "USER",
                 val error: String = "")
