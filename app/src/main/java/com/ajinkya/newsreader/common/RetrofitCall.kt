package com.ajinkya.newsreader.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCall {

    private var retrofitClient:Retrofit?=null

    fun getClient(baseURL: String?):Retrofit{
        if (retrofitClient ==null){
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseURL!!)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient!!
    }
}