package com.example.advancedandroidcourse.model

import androidx.annotation.Keep

@Keep
data class Respuesta(
    val id: Int,
    val password: String,
    val username: String
)