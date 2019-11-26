package com.example.advancedandroidcourse.repository

import com.example.advancedandroidcourse.data.network.`interface`.UserInterface
import com.example.advancedandroidcourse.model.Request

interface UserRepository{
    suspend fun obtainUsers(): Request
}

class UserRepositoryImpl(private val userServices: UserInterface): UserRepository{
    override suspend fun obtainUsers() = userServices.retrieveUsers()

}