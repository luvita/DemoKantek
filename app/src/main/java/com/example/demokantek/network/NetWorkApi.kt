package com.example.demokantek.network

import com.example.demokantek.model.BaseResponse
import com.example.demokantek.model.UserInfo
import com.example.demokantek.model.requestLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NetWorkApi {

    @POST("user/login")
    fun getUserInfo(@Body resultLogin: requestLogin): Call<BaseResponse<UserInfo>>
}