package com.room.fashion.view

import android.view.View
import com.room.fashion.model.response.FashionResponse
import kr.lazynight.android.adapter.FashionListAdapter

interface OnItemClickListener{
    fun onBannerItemClicked (bannerItem: FashionResponse.FashionBanner)

    fun onItemClick (holder: FashionListAdapter.ImageHolder, view: View, position: Int)
}