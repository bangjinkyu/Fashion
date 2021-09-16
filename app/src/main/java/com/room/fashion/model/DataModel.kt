package com.room.fashion.model

import io.reactivex.Single
import com.room.fashion.model.response.FashionResponse
import retrofit2.Call

interface DataModel {
    fun getData(): Call<FashionResponse>
    fun getGoodData(query: Int): Single<FashionResponse>
}