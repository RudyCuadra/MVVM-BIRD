package com.example.advancedandroidcourse.data.network.`interface`

import com.example.advancedandroidcourse.model.Request
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserInterface {
    @GET("/obtenerUsuariosDeBD")
    suspend fun retrieveUsers(): Request

//    @POST
//    suspend fun requestPost(@Body miJsonBody: Model)
}