package com.room.fashion.model

import com.room.fashion.network.FashionService
import com.room.fashion.util.ApiResult

class  DataModelImpl(private val service: FashionService): DataModel {

    override suspend fun getData(): FashionResponse {
        return service.getFashion()
    }

    override suspend fun getGoodData(query: Int): ApiResult<FashionGoods> {
        val response = service.getFashionGood(lastId = query)
        if (response.isSuccessful) {
            response.body()?.let {
                return ApiResult.Success(it)
            }
        }
        return ApiResult.Error("error")
    }
}
