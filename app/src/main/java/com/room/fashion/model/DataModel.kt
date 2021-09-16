package com.room.fashion.model

import io.reactivex.Single
import com.room.fashion.model.response.FashionResponse
import retrofit2.Call

interface DataModel {
    suspend fun getData(): FashionResponse
    suspend fun getGoodData(query: Int): FashionResponse
}