package com.maxden.sushi

import android.app.Application
import com.maxden.sushi.di.repositoryModule
import com.maxden.sushi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}