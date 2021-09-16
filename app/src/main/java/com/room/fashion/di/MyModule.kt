package com.room.fashion.di

import com.room.fashion.FashionListAdapter
import com.room.fashion.model.DataModel
import com.room.fashion.model.DataModelImpl
import com.room.fashion.model.service.FashionService
import com.room.fashion.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitPart = module {
    single<FashionService> {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FashionService::class.java)
    }
}

var adapterPart = module {
    factory {
        FashionListAdapter()
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)