package com.room.fashion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.room.fashion.base.BaseViewModel
import com.room.fashion.model.FashionResponse

class MainViewModel : BaseViewModel() {
    val shareLiveData: MutableLiveData<List<FashionResponse.FashionGood>> = MutableLiveData()

    fun setLiveData(list: List<FashionResponse.FashionGood>) {
        shareLiveData.value = list
    }
}