package com.example.mvvmwitherrorhandling.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.mvvmwitherrorhandling.models.QuoteList
import com.example.mvvmwitherrorhandling.repositeries.QuoteRepository
import com.example.mvvmwitherrorhandling.repositeries.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes:LiveData<Response<QuoteList>>
    get() = repository.quotes
}