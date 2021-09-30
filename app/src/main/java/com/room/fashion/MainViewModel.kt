package com.room.fashion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.room.fashion.base.BaseViewModel
import com.room.fashion.model.FashionResponse

class MainViewModel : BaseViewModel() {
    private val _shareLiveData: MutableLiveData<List<FashionResponse.FashionGood>> = MutableLiveData()

    val getShareLiveData: LiveData<List<FashionResponse.FashionGood>>
    get()= _shareLiveData

    fun setLiveData(list: List<FashionResponse.FashionGood>) {
        _shareLiveData.value = list
    }
}