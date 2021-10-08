package com.room.fashion.view.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.room.fashion.base.BaseViewModel
import com.room.fashion.model.FashionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel : BaseViewModel() {
    private val TAG = "FavoriteViewModel"

    val fashionGoodLiveData: MutableLiveData<List<FashionResponse.FashionGood>> = MutableLiveData()

    fun getFashionFavorite(list: List<FashionResponse.FashionGood>) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                fashionGoodLiveData.value = list
            }
        }
    }
}