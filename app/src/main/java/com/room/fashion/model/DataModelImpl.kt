package com.room.fashion.model

import com.room.fashion.network.FashionService

class  DataModelImpl(private val service: FashionService): DataModel {

    override suspend fun getData(): FashionResponse {
        return service.getFashion()
    }

    override suspend fun getGoodData(query: Int): FashionResponse {
        return service.getFashionGood(lastId = query)
    }
}
