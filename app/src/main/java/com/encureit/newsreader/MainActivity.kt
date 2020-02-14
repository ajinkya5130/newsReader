package com.encureit.newsreader

import NewsModel
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.encureit.newsreader.Common.common
import com.encureit.newsreader.adapters.NewsAdapter
import com.encureit.newsreader.listeners.NewsService
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import io.paperdb.Paper

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var newsService: NewsService
    lateinit var adapter: NewsAdapter
    lateinit var dialog: AlertDialog
    var initVal: Int = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper.init(this)
        newsService = common.newsService

        swipeContainer.setOnRefreshListener {

            if (initVal%3 == 0){
                tv_news_type.setText("Technology")
                loadNews(true,newsService.fetchLatestTechnology());

            }else if (initVal%3 == 1){
                tv_news_type.setText("Latest News")
                loadNews(true,newsService.fetchLatestNewsAsync());


            }else{
                tv_news_type.setText("Entertainment")
                loadNews(true,newsService.fetchLatestEntertainment());


            }
            initVal++;
        }

        rv_news.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)

        rv_news.layoutManager = layoutManager

        dialog = SpotsDialog(this)

        loadNews(false, newsService.fetchLatestTechnology())


    }

    private fun loadNews(b: Boolean, fetchLatestTechnology: Call<NewsModel>) {

        if (!b){
            try {
                val cache = Paper.book().read<String>("cache")
                if (cache!=null && !cache.isBlank() && cache!="null"){
                    val newsModel = Gson().fromJson<NewsModel>(cache,NewsModel::class.java)
                    adapter = NewsAdapter(this,newsModel)
                    adapter.notifyDataSetChanged()
                    rv_news.adapter = adapter
                    swipeContainer.isRefreshing = false
                    Toast.makeText(this@MainActivity,"Loaded data from Cache",Toast.LENGTH_LONG).show()
                }else{
                    dialog.show()
                    loadNewsData(b,fetchLatestTechnology)
                }
            } catch (e: Exception) {
            }
        }else{
            swipeContainer.isRefreshing = true

            loadNewsData(false,fetchLatestTechnology)
        }

    }

    private fun loadNewsData(
        refreshing: Boolean,
        fetchLatestNewsAsync: Call<NewsModel>
    ) {

        fetchLatestNewsAsync.enqueue(object :retrofit2.Callback<NewsModel>{
            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@MainActivity,"Error while Loading",Toast.LENGTH_LONG).show()
                swipeContainer.isRefreshing = false
            }

            override fun onResponse(
                call: Call<NewsModel>,
                response: Response<NewsModel>
            ) {

                adapter = NewsAdapter(this@MainActivity,response.body()!!)
                adapter.notifyDataSetChanged()
                rv_news.adapter =adapter
                Toast.makeText(this@MainActivity,"Loaded News",Toast.LENGTH_LONG).show()
                Paper.book().write("cache",Gson().toJson(response.body()))
                dialog.dismiss()

                swipeContainer.isRefreshing = refreshing

            }
        })
    }
}
