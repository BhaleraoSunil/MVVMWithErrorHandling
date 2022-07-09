package com.example.mvvmwitherrorhandling.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmwitherrorhandling.models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun  addQuotes(list: List<Result>)

    @Query("select * from quote")
     fun  getQuotes():List<Result>

}