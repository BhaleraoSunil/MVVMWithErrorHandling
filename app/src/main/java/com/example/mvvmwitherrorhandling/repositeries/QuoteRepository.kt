package com.example.mvvmwitherrorhandling.repositeries

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmwitherrorhandling.api.NetworkUtils
import com.example.mvvmwitherrorhandling.api.QuoteService
import com.example.mvvmwitherrorhandling.db.QuoteDatabase
import com.example.mvvmwitherrorhandling.models.QuoteList

class QuoteRepository(private val quoteService: QuoteService, private val quoteDatabase: QuoteDatabase,
                      private val appContext: Context
) {


    private val  quotesLiveData = MutableLiveData<Response<QuoteList>>()

    val quotes : LiveData<Response<QuoteList>>
    get() = quotesLiveData

    suspend fun  getQuotes(page:Int){

        if(NetworkUtils.isInternetAvailable(appContext)){
            try {
                val result = quoteService.getQuotes(page)
                if(result.body() != null){
                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                    quotesLiveData.postValue(Response.Success(result.body()))
                }
            } catch (e: Exception) {
                quotesLiveData.postValue(Response.Error(e.message.toString()))

            }

        }else{
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quotesList = QuoteList(1,1,1,quotes,1,1)
            quotesLiveData.postValue(Response.Success(quotesList))
        }
    }


}