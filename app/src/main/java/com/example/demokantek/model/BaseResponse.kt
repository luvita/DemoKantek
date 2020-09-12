package com.example.demokantek.model

class BaseResponse<T> {
    val result: Boolean? = false
    val message: String? = null
    val data: T?= null
}