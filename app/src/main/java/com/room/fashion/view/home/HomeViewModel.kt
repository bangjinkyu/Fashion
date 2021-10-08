package com.room.fashion.view.home

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

    val fashionGoodLiveData: MutableLiveData<List<FashionResponse.FashionGood>> = MutableLiveData()
    val bannerItemList: MutableLiveData<List<FashionResponse.FashionBanner>> = MutableLiveData()
    val currentPosition: MutableLiveData<Int> = MutableLiveData()

    val resultLiveData: MutableLiveData<ApiResult<FashionGoods>> = MutableLiveData()

    init{
        currentPosition.value = 0
    }

    fun setCurrentPosition(position: Int) {
        currentPosition.value = position
    }

    fun getCurrentPosition() = currentPosition.value

    fun getBannerItems() {
        viewModelScope.launch {
            withContext(Main) {
                bannerItemList.value = model.getData().banners
            }
        }
    }

    fun getFashionSearch() {
        viewModelScope.launch {
            withContext(Main) {
                fashionGoodLiveData.value = model.getData().goods
            }
        }
    }

    fun getFashionPage(page: Int) {
        viewModelScope.launch {
            withContext(Main) {
                resultLiveData.value = ApiResult.Loading("Loading...")
                val result = model.getGoodData(page)
                resultLiveData.value = result
                when (result){
                    is ApiResult.Success -> {
                        fashionGoodLiveData.value = result.data.goods
                    }
                }
            }
        }
    }
}