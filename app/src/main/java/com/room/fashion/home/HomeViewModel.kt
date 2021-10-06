package com.room.fashion.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.room.fashion.base.BaseViewModel
import com.room.fashion.model.DataModel
import com.room.fashion.model.FashionGoods
import com.room.fashion.model.FashionResponse
import com.room.fashion.model.FashionResponse.Companion.loading
import com.room.fashion.util.ApiResult
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val model: DataModel) : BaseViewModel() {
    private val TAG = "HomeViewModel"

    private val _fashionGoodLiveData: MutableLiveData<List<FashionResponse.FashionGood>> = MutableLiveData()
    private val _bannerItemList: MutableLiveData<List<FashionResponse.FashionBanner>> = MutableLiveData()
    private val _currentPosition: MutableLiveData<Int> = MutableLiveData()

    private val _resultLiveData = MutableLiveData<FashionResponse.FashionStatus>()

    val bannerItemList: LiveData<List<FashionResponse.FashionBanner>>
        get() = _bannerItemList

    val currentPosition: LiveData<Int>
        get() = _currentPosition

    val fashionGoodLiveData: LiveData<List<FashionResponse.FashionGood>>
        get() = _fashionGoodLiveData

    val resultLiveData :  LiveData<FashionResponse.FashionStatus>
        get() = _resultLiveData

    init{
        _currentPosition.value = 0
    }

    fun setCurrentPosition(position: Int) {
        _currentPosition.value = position
    }

    fun getCurrentPosition() = currentPosition.value

    fun getBannerItems() {
        viewModelScope.launch {
            withContext(Main) {
                _bannerItemList.value = model.getData().banners
            }
        }
    }

    fun getFashionSearch() {
        viewModelScope.launch {
            withContext(Main) {
                _fashionGoodLiveData.value = model.getData().goods
            }
        }
    }

    fun getFashionPage(page: Int) {
        viewModelScope.launch {
            withContext(Main) {
                _resultLiveData.value = loading()
                val result = model.getGoodData(page)
                when (result){
                    is ApiResult.Success -> {
                        _fashionGoodLiveData.value = result.data.goods
                        _resultLiveData.value = FashionResponse.success(result.data)
                    }
                    is ApiResult.Error -> {
                        _resultLiveData.value = FashionResponse.error(result.error)
                    }
                }
            }
        }
    }
}