package com.example.demokantek.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demokantek.ICallback.ICallbackLoad
import com.example.demokantek.model.BaseResponse
import com.example.demokantek.model.UserInfo
import com.example.demokantek.model.requestLogin
import com.example.demokantek.repository.LoginRepository
import org.koin.standalone.KoinComponent

class loginViewModel(val dataRepository: LoginRepository) : ViewModel(), KoinComponent, ICallbackLoad<UserInfo> {

    var userInfoLiveData: MutableLiveData<UserInfo>?= null
    var messageError: MutableLiveData<String>?= null
    private lateinit var LoadUserInfoCallbackListener: ICallbackLoad<UserInfo>
    var resLogin: requestLogin? = null;

    init{
        LoadUserInfoCallbackListener = this
    }

    fun getBodyRequest(result: requestLogin) {
        resLogin = result
    }

    val userData: LiveData<UserInfo>
        get() {
            userInfoLiveData = MutableLiveData()
            messageError = MutableLiveData()
            onLogin()

            return userInfoLiveData!!
        }

    fun onLogin() {
        dataRepository.onLogin(resLogin!!, object : LoginRepository.icallLogin{
            override fun onSuccess(data: BaseResponse<UserInfo>) {
                LoadUserInfoCallbackListener.onLoadSuccess(data?.data!!)
            }

            override fun onFailure(message: String?) {
                LoadUserInfoCallbackListener.onLoadFailed(message!!)
            }

        })
    }

    override fun onLoadSuccess(data: UserInfo) {
        userInfoLiveData!!.value = data
    }

    override fun onLoadFailed(message: String) {
        messageError!!.value = message
    }
}