package com.example.schoolapps.ViewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapps.R
import com.example.schoolapps.Database.News

class RecyclerAdapter internal constructor (context: Context): RecyclerView.Adapter<RecyclerAdapter.NewsHolder>() {
//    private  lateinit  var ss:FragmentNewsDetail
    private var list = emptyList<News>()
    //private lateinit var clickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return NewsHolder(v)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getNewsAtPosition(position : Int) : News {
        return list.get(position)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news= list.get(position)
        holder.title.text = news.title
        holder.date.text = news.datepublish
        holder.desc.text = news.description

    }

    class NewsHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
            val title : TextView = itemView.findViewById(R.id.lbltitle)
            val date : TextView = itemView.findViewById(R.id.date)
            val desc : TextView = itemView.findViewById(R.id.lbldescsummary)

    }

    internal fun setNews(news: List<News>) {
        this.list = news
        notifyDataSetChanged()
    }

}