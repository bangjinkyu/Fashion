package com.room.fashion.model

import com.google.gson.annotations.SerializedName

data class FashionGoods(
    @SerializedName("goods")
    val goods: List<FashionResponse.FashionGood>
)
