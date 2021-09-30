package com.room.fashion

import android.app.Application
import android.content.Context
import com.room.fashion.di.myDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FashionApplication : Application() {

    init{
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@FashionApplication )
            koin.loadModules(myDiModule)
        }
    }

    companion object {
        lateinit var instance: FashionApplication
        fun getInstance(): Context {
            return instance.applicationContext
        }
    }
}