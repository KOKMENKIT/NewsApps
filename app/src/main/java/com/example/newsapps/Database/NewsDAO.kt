package com.example.schoolapps.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDAO {
    @Query("SELECT * FROM News")
    fun getAllNews(): LiveData<List<News>>

    @Query("SELECT * FROM News WHERE title LIKE :searchresult")
    fun getNewsResult(searchresult: String): LiveData<List<News>>

    @Update
    suspend fun update(vararg n: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(n: News)

    @Delete
    suspend fun delete(n: News)

    @Query("DELETE FROM News")
    suspend fun deleteAll()
}