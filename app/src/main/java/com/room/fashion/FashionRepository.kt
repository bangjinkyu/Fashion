package com.room.fashion

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FashionRepository {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl( "http://d2bab9i9pr8lds.cloudfront.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : FashionApi by lazy {
        retrofit.create(FashionApi::class.java)
    }
}