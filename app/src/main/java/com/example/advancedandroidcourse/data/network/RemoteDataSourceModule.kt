package com.example.advancedandroidcourse.data.network

import com.example.advancedandroidcourse.data.network.`interface`.UserInterface
import com.example.advancedandroidcourse.data.network.adapters.CoroutineCallAdapterFactory
import com.example.advancedandroidcourse.data.network.headerInterceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val miUrl = "http://192.168.1.6:3000/"

val remoteDataSourceModule = module {
    single { createOkHttpClient() }
    single { createWebService<UserInterface>(get(), miUrl) }
}

fun createOkHttpClient(): OkHttpClient{
    return getOkHttpClient()
}

fun getOkHttpClient(): OkHttpClient{
    return OkHttpClient
        .Builder()
        .addInterceptor(HeaderInterceptor())
        .apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T{
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}