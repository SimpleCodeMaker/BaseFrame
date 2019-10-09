package com.casual.baseframe.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel


abstract class BFActivity<T : ViewModel> : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(layoutId)
        super.onCreate(savedInstanceState)
        initView(savedInstanceState)
    }

    abstract val layoutId: Int
    abstract fun initView(savedInstanceState: Bundle?)
    abstract val viewModel: T
    override fun onDestroy() {
        super.onDestroy()
    }

}
