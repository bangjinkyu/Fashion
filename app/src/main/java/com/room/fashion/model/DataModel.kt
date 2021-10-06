package com.room.fashion.model

import com.room.fashion.util.ApiResult

interface DataModel {
    suspend fun getData(): FashionResponse
    suspend fun getGoodData(query: Int): ApiResult<FashionGoods>
}