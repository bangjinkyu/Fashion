package com.room.fashion.util

import android.view.View
import com.room.fashion.adapter.FashionListAdapter
import com.room.fashion.model.FashionResponse

interface OnItemClickListener{
    fun onBannerItemClicked (bannerItem: FashionResponse.FashionBanner)

    fun onItemClick (holder: FashionListAdapter.ImageHolder, view: View, position: Int)
}