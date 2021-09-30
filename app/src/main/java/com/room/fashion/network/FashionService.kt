package com.room.fashion.network

import com.room.fashion.model.FashionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FashionService {
    @GET("/api/home")
    fun getFashion(): FashionResponse

    @GET("/api/home/good")
    fun getFashionGood(
        @Query("lastId") lastId: Int
    ) : FashionResponse
}