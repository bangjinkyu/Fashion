package com.room.fashion

import com.google.gson.annotations.SerializedName

data class FashionSearch(
    @SerializedName("banners") val banners: FashionBanner,
    @SerializedName("goods") val goods: List<FashionGood>
)
