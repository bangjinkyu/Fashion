package com.room.fashion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.room.fashion.Base.BaseViewModel
import com.room.fashion.model.DataModel
import com.room.fashion.model.response.FashionResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val model: DataModel) : BaseViewModel() {

    private val _fashionResponseLiveData = MutableLiveData<FashionResponse>()

    val fashionResponseLiveData: LiveData<FashionResponse>
        get() = _fashionResponseLiveData

    fun getFashionSearch(query: Int) {
        addDisposable(model.getGoodData(query)  //rxjava
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            it.run {
                if (goods.isNotEmpty()) {
                    _fashionResponseLiveData.postValue(this)
                }
            }
        }, {
            Log.d("room", "response error, message : ${it.message}")
        }))
    }
}