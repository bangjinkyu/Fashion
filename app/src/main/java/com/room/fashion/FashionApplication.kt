package com.room.fashion

import android.app.Application
import android.content.Context

class FashionApplication : Application() {

    init{
        instance = this
    }

    companion object {
        lateinit var instance: FashionApplication
        fun getInstance(): Context {
            return instance.applicationContext
        }
    }
}