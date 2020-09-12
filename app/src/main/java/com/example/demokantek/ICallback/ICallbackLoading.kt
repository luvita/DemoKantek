package com.example.demokantek.ICallback

interface ICallbackLoad<T> {

    fun onLoadSuccess(data: T)
    fun onLoadFailed(message: String)

}