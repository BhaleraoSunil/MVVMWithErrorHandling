package com.example.mvvmwitherrorhandling

import android.app.Application
import com.example.mvvmwitherrorhandling.api.QuoteService
import com.example.mvvmwitherrorhandling.api.RetrofitHelper
import com.example.mvvmwitherrorhandling.db.QuoteDatabase
import com.example.mvvmwitherrorhandling.repositeries.QuoteRepository

class QuoteApplication:Application() {

    lateinit var respository: QuoteRepository

    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)


        val database = QuoteDatabase.getDatabase(applicationContext)

        respository =  QuoteRepository(quoteService,database, appContext = applicationContext)

    }
}