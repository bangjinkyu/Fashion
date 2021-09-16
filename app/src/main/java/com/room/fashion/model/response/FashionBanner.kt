package com.room.fashion.model.response

import com.google.gson.annotations.SerializedName

data class FashionBanner(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: Int
)
