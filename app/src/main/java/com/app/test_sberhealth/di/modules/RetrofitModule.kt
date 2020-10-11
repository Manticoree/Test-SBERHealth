package com.app.test_sberhealth.di.modules

import com.app.test_sberhealth.retrofit.RetrofitApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetrofitModule(private val url: String) {

    @Provides
    @Singleton
    fun getRetrofitApi(): RetrofitApi {
        return getClient(url).create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun getClient(url: String): Retrofit {
        val gsonBuilder = GsonBuilder()
        return Retrofit.Builder().baseUrl(url)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(OkHttpClient.Builder().build())
            .build()
    }

}