package com.barryzea.composeapp.data.remoteDatasource

import com.barryzea.composeapp.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 02/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

class RetrofitServiceImpl:RetrofitServiceInterface {
    private val okHttpClient=HttpLoggingInterceptor().run{
            level=HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(this).build()
    }
    override fun starWarsApiService(): StarWarsApi {
        val service= Retrofit.Builder()
            .baseUrl(Constants.URL_STAR_WARS_API)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return service.create(StarWarsApi::class.java)
    }

}