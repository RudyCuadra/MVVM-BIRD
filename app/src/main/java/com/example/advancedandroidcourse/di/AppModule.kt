package com.example.advancedandroidcourse.di

import com.example.advancedandroidcourse.data.network.remoteDataSourceModule
import com.example.advancedandroidcourse.repository.UserRepository
import com.example.advancedandroidcourse.repository.UserRepositoryImpl
import com.example.advancedandroidcourse.ui.viewModels.UserViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { UserViewModel(get()) }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}

val allAppModules = listOf(appModule, remoteDataSourceModule)