package com.example.schoolapps.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.schoolapps.Database.AppDB
import com.example.schoolapps.Database.News
import kotlinx.coroutines.launch

class NewsViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository

    val allnews: LiveData<List<News>>

    init {
        val newsDao = AppDB.getInstance(application, viewModelScope).newsDao()
        repository = Repository(newsDao)
        allnews = repository.allNews
    }

    fun insert(news: News) = viewModelScope.launch{
        repository.insert(news)
    }

    fun update(news: News) = viewModelScope.launch {
        repository.update(news)
    }

    fun delete(news: News) = viewModelScope.launch {
        repository.delete(news)
    }


}