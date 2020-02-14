package com.ajinkya.newsreader.adapters

import com.ajinkya.newsreader.models.NewsModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ajinkya.newsreader.R
import com.ajinkya.newsreader.listeners.AdapterItemClickListener
import com.ajinkya.newsreader.viewHolder.NewsAdapterViewHolder

class NewsAdapter (private val context: Context,
                   private val newsModel: NewsModel
):
        RecyclerView.Adapter<NewsAdapterViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NewsAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.single_item_layout,parent,false)
        return NewsAdapterViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return newsModel.articles.size


    }

    override fun onBindViewHolder(holder: NewsAdapterViewHolder, position: Int) {

//        holder.newsTitle.text = newsModel.articles.get(position).title
        holder.newsTitle.text = newsModel.articles[position].title

        holder.setItemClickListener(object : AdapterItemClickListener{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context,"will do it later",Toast.LENGTH_LONG).show()
            }
        })
    }


}