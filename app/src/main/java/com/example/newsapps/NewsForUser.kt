package com.example.newsapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapps.ViewModel.NewsViewModel
import com.example.schoolapps.ViewModel.RecyclerAdapter

class NewsForUser : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_for_user)

        //for recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = RecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //get a existing model view model for live data
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        newsViewModel.allnews.observe(this, Observer { list ->
            list?.let {
                adapter.setNews(it)
            }
        })
    }
}
