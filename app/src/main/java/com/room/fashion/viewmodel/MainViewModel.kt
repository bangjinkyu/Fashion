package com.room.fashion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.room.fashion.Base.BaseViewModel
import com.room.fashion.model.DataModel
import com.room.fashion.model.response.FashionResponse

class MainViewModel(private val model: DataModel) : BaseViewModel() {

    private val _shareLiveData: MutableLiveData<List<FashionResponse.FashionGood>> = MutableLiveData()

    val shareLiveData: LiveData<List<FashionResponse.FashionGood>>
        get() = _shareLiveData

    fun getLiveData() = _shareLiveData.value


    fun setLiveData(list: List<FashionResponse.FashionGood>) {
        _shareLiveData.value = list
    }
}