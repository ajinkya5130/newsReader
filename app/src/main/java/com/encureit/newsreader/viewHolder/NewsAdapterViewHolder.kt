package com.encureit.newsreader.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.encureit.newsreader.listeners.AdapterItemClickListener
import kotlinx.android.synthetic.main.single_item_layout.view.*

class NewsAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
    private lateinit var itemClickListener: AdapterItemClickListener
    var newsTitle = itemView.tv_news_heading

    fun setItemClickListener(itemClickListener: AdapterItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {

        itemClickListener.onClick(v!!,adapterPosition)

    }

}