package com.example.mvvmwitherrorhandling.repositeries

import com.example.mvvmwitherrorhandling.models.QuoteList

/*sealed class Response{
    class Loading:Response()
    class Success(val quoteList: QuoteList):Response()
    class Error(val errorMessage:String):Response()

}*/

//improved  version of above class
/*

sealed class Response(val data:QuoteList?=null,val errorMessage: String?=null){
    class Loading:Response()
    class Success(quoteList: QuoteList):Response(data = quoteList)
    class Error( errorMessage:String):Response(errorMessage = errorMessage)

}*/


// problem in above approach is we need to create multiple response class
// for each new api response type
// to resolve this problem we have create generic response class
// which will handle all response type
sealed class Response<T>(val data:T?=null,val errorMessage: String?=null){
    class Loading<T>:Response<T>()
    class Success<T>(data: T?):Response<T>(data = data)
    class Error<T>( errorMessage:String):Response<T>(errorMessage = errorMessage)

}
