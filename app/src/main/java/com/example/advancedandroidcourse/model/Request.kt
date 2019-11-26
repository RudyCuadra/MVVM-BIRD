package com.example.advancedandroidcourse.model

import androidx.annotation.Keep

@Keep
data class Request(
    val respuesta: List<Respuesta>
)