package com.room.fashion

import com.room.fashion.base.BaseViewModel
import com.room.fashion.model.FashionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : BaseViewModel() {
    val shareLiveData = mutableListOf<FashionResponse.FashionGood>()

    fun setLiveData(list: List<FashionResponse.FashionGood>) {
        shareLiveData.addAll(list)
    }
}