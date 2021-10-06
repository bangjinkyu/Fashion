package com.room.fashion.di

import com.room.fashion.BuildConfig
import com.room.fashion.adapter.FashionListAdapter
import com.room.fashion.adapter.ViewPagerAdapter
import com.room.fashion.model.DataModel
import com.room.fashion.model.DataModelImpl
import com.room.fashion.network.FashionService
import com.room.fashion.home.HomeViewModel
import com.room.fashion.favorite.FavoriteViewModel
import com.room.fashion.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitPart = module {
    single<FashionService> {
        Retrofit.Builder()
            .baseUrl("http://d2bab9i9pr8lds.cloudfront.net/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(getHttpLoggingInterceptor())
            }.build())
            .build()
            .create(FashionService::class.java)
    }
}

private fun getHttpLoggingInterceptor() : HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return httpLoggingInterceptor
}

var adapterPart = module {
    single {
        FashionListAdapter()
    }

    single {
        ViewPagerAdapter()
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel() }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)