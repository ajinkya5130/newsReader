package com.encureit.newsreader.Common

import com.encureit.newsreader.listeners.NewsService

object common{

    val BASE_URL = "https://newsapi.org"
    val API_KEY = "beae63bb4dbd4283a81ba08067fb7d71"

    val newsService:NewsService
    get() = RetrofitCall.getClient(BASE_URL).create(NewsService::class.java)
}