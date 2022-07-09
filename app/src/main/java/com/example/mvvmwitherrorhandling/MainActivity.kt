package com.example.mvvmwitherrorhandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwitherrorhandling.api.QuoteService
import com.example.mvvmwitherrorhandling.api.RetrofitHelper
import com.example.mvvmwitherrorhandling.repositeries.QuoteRepository
import com.example.mvvmwitherrorhandling.repositeries.Response
import com.example.mvvmwitherrorhandling.viewmodels.MainViewModel
import com.example.mvvmwitherrorhandling.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var  mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        //val respository =  QuoteRepository(quoteService)

        val respository =  (application as QuoteApplication).respository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(respository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, {

            when(it){

                is Response.Loading->{
                    Toast.makeText(applicationContext, "loading",Toast.LENGTH_LONG).show()

                }
                is Response.Error->{
                    Toast.makeText(applicationContext, "Error -->${it.errorMessage}",Toast.LENGTH_LONG).show()

                }
                is Response.Success->{
                    it.data?.let {
                        Toast.makeText(applicationContext, it.results.size.toString(),Toast.LENGTH_LONG).show()

                    }

                }
            }
          // Log.e(javaClass.name,"quotes size---> ${it.results.size}")
           // Log.e(javaClass.name,"quotes---> ${it.results.toString()}")
        })
    }
}