package com.barryzea.composeapp.common

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 02/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

abstract class CallBackWithRetry<T>(call: Call<T>):Callback<T> {
    private val TOTAL_RETRIES_ENTRY=10
    var callMain:Call<T>?=call
    private var retryCount=0

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if(!response.isSuccessful && retryCount++ < TOTAL_RETRIES_ENTRY)retry() else onFinalResponse(call,response,retryCount)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {

    }
    private fun retry(){
        callMain?.clone()?.enqueue(this)
    }
    open fun onFinalResponse(call: Call<T>, response:Response<T>, numRetry:Int){}
    open fun onFinalFailure(call:Call<T>, t:Throwable, numRetry: Int){}
}