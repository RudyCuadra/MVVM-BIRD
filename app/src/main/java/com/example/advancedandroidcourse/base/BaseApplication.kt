package com.example.advancedandroidcourse.base

import android.app.Application
import com.example.advancedandroidcourse.di.allAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    companion object {
        lateinit var context: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin {
            androidLogger()
            androidContext(
                this@BaseApplication
            )
            modules(allAppModules)
        }
    }
}