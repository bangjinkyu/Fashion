package com.room.fashion.model.service

import io.reactivex.Single
import com.room.fashion.model.response.FashionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FashionService {
    @GET("/api/home")
    fun getFashion(): Call<FashionResponse>

    @GET("/api/home/good")
    fun getFashionGood(
        @Query("lastId") lastId: Int
    ) : Single<FashionResponse>
}