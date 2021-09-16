package com.room.fashion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.room.fashion.Base.BaseViewModel
import com.room.fashion.model.response.FashionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel : BaseViewModel() {
    private val TAG = "FavoriteViewModel"

    private val _fashionGoodLiveData: MutableLiveData<List<FashionResponse.FashionGood>> = MutableLiveData()


    val fashionGoodLiveData: LiveData<List<FashionResponse.FashionGood>>
        get() = _fashionGoodLiveData

    fun getFashionFavorite(list: List<FashionResponse.FashionGood>) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _fashionGoodLiveData.value = list
            }
        }
    }

    fun getImageView() {

    }
}