package com.room.fashion

import com.google.gson.annotations.SerializedName

data class FashionBanner(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: Int
)
