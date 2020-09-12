package com.example.demokantek.ICallback

import android.view.View

interface ICallbackBase<T> {
    fun initUI(root: View)
    fun setupUI(data: T)
    fun setupObservers()
}