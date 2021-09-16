package com.room.fashion

import android.app.Application
import com.room.fashion.di.myDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FashionApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@FashionApplication )
            modules(myDiModule)
        }
    }
}