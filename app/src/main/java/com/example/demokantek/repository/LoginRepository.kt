package com.example.demokantek.repository

import android.util.Log
import com.example.demokantek.model.BaseResponse
import com.example.demokantek.model.UserInfo
import com.example.demokantek.model.requestLogin
import com.example.demokantek.network.NetWorkApi
import retrofit2.Call
import retrofit2.Response

class LoginRepository(val netWorkApi: NetWorkApi) {

    interface icallLogin {
        fun onSuccess(data: BaseResponse<UserInfo>)
        fun onFailure(message: String?)
    }

    fun onLogin(account: requestLogin, login: icallLogin) {
        netWorkApi.getUserInfo(account).enqueue(object : retrofit2.Callback<BaseResponse<UserInfo>> {
            override fun onFailure(call: Call<BaseResponse<UserInfo>>, t: Throwable) {
                login.onFailure(t!!.message!!.toString())
            }

            override fun onResponse(call: Call<BaseResponse<UserInfo>>, response: Response<BaseResponse<UserInfo>>) {
                when(response.body()!!.result) {
                    true -> login.onSuccess((response.body() as BaseResponse<UserInfo>))
                    false -> login.onFailure("sai username hoac password")
                }
            }

        })
    }

}