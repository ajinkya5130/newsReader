package com.ajinkya.newsreader.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajinkya.newsreader.listeners.AdapterItemClickListener
import kotlinx.android.synthetic.main.single_item_layout.view.*

class NewsAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
    private lateinit var itemClickListener: AdapterItemClickListener
    var newsTitle: TextView = itemView.tv_news_heading

    fun setItemClickListener(itemClickListener: AdapterItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {

        itemClickListener.onClick(v!!,adapterPosition)

    }

}