package com.room.fashion.model.response

import com.google.gson.annotations.SerializedName
import com.room.fashion.model.response.FashionBanner
import com.room.fashion.model.response.FashionGood

data class FashionSearch(
    @SerializedName("banners") val banners: List<FashionBanner>,
    @SerializedName("goods") val goods: List<FashionGood>
)
