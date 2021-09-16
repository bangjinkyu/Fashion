package com.room.fashion.model.service

import com.room.fashion.model.response.FashionSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FashionApi {
    @GET("/api/home/goods")
    fun getFashionName(
        @Query("lastId") lastId: Int
    ): Call<FashionSearch>
}