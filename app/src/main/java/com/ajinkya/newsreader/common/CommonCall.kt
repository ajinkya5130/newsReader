package com.ajinkya.newsreader.common

import com.ajinkya.newsreader.listeners.NewsService

object CommonCall{

    private const val BASE_URL = "https://newsapi.org"
    val API_KEY = "beae63bb4dbd4283a81ba08067fb7d71"
    var country = "in"

    val newsService:NewsService
    get() = RetrofitCall.getClient(BASE_URL).create(NewsService::class.java)


    fun getNewsApi(source: String): String {
        val apiUrl = StringBuilder("http://newsapi.org/v2/top-headlines?sources=")
            .append(source)
            .append("@apiKey=")
            .append(API_KEY)
            .toString()

        return apiUrl
    }
}