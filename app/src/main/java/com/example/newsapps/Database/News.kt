package com.example.schoolapps.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class News (
    @PrimaryKey(autoGenerate = true)
    var newsCode: Int,

    @ColumnInfo(name = "title")
    var title: String ,

    @ColumnInfo(name = "description")
    var description: String ,

    @ColumnInfo(name = "datepublish")
    var datepublish: String
)
