package com.ajinkya.newsreader.listeners

import com.ajinkya.newsreader.models.NewsModel
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {

    @GET("/v2/top-headlines?country=in&apiKey=beae63bb4dbd4283a81ba08067fb7d71")
    fun fetchLatestNewsAsync() : Call<NewsModel>

    @GET("v2/top-headlines?country=in&category=technology&apiKey=beae63bb4dbd4283a81ba08067fb7d71")
    fun fetchLatestTechnology() : Call<NewsModel>

    @GET("v2/top-headlines?country=in&category=entertainment&apiKey=beae63bb4dbd4283a81ba08067fb7d71")
    fun fetchLatestEntertainment() : Call<NewsModel>

   /* @get:GET("/v2/top-headlines?country=in&apiKey=beae63bb4dbd4283a81ba08067fb7d71")

    val sources: Call<com.encureit.newsreader.models.NewsModel>*/

}