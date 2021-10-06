package com.room.fashion.model

interface DataModel {
    suspend fun getData(): FashionResponse
    suspend fun getGoodData(query: Int): FashionGoods
}