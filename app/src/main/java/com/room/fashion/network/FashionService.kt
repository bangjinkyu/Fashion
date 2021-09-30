package com.room.fashion.network

import com.room.fashion.model.FashionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FashionService {
    @GET("/api/home")
    suspend fun getFashion(): FashionResponse

    @GET("/api/home/good")
    suspend fun getFashionGood(
        @Query("lastId") lastId: Int
    ) : FashionResponse
}