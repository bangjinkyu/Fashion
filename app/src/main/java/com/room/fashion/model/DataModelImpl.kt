package com.room.fashion.model

import io.reactivex.Single
import com.room.fashion.model.response.FashionResponse
import com.room.fashion.model.service.FashionService
import retrofit2.Call

class  DataModelImpl(private val service:FashionService): DataModel {

    override fun getData(): Call<FashionResponse> {
        return service.getFashion()
    }

    override fun getGoodData(query: Int): Single<FashionResponse> {
        return service.getFashionGood(lastId = query)
    }
}
