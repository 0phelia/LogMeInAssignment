package com.bachelor.arty.logmeinassignment

import android.app.Application
import com.bachelor.arty.logmeinassignment.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate(){
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(AppModule.getModule())
        }
    }
}