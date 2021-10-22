package com.room.fashion.di

import com.room.fashion.BuildConfig
import com.room.fashion.adapter.FashionListAdapter
import com.room.fashion.adapter.ViewPagerAdapter
import com.room.fashion.model.DataModel
import com.room.fashion.model.DataModelImpl
import com.room.fashion.network.FashionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://d2bab9i9pr8lds.cloudfront.net/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
        }.build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit) : FashionService {
        return retrofit.create(FashionService::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(fashionService: FashionService): DataModel {
        return DataModelImpl(fashionService)
    }

    @Provides
    @Singleton
    fun provideAdapter(): FashionListAdapter = FashionListAdapter()

    @Provides
    @Singleton
    fun provideViewPagerAdapter(): ViewPagerAdapter = ViewPagerAdapter()
}

