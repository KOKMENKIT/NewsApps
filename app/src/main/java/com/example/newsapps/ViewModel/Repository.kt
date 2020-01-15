package com.example.schoolapps.ViewModel

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.schoolapps.Database.News
import com.example.schoolapps.Database.NewsDAO

class Repository(private val newsDao: NewsDAO) {
    val allNews: LiveData<List<News>> = newsDao.getAllNews()

    suspend fun insert(news: News) {
        newsDao.insert(news)
    }

    suspend fun update(news: News) {
        newsDao.update(news)
    }

    suspend fun delete(news: News) {
        newsDao.delete(news)
    }

}