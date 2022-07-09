package com.example.mvvmwitherrorhandling.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class Result(

    @PrimaryKey(autoGenerate = true)
    val quoteId:Int,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int
)