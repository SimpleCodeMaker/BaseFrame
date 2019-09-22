package com.casual.baseframe.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.casual.baseframe.utils.ActivityStackManager


abstract class BFActivity<T : ViewModel> : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(layoutId)
        super.onCreate(savedInstanceState)
        initView(savedInstanceState)
        //加入activity栈
        ActivityStackManager.activityList.add(this)
    }

    abstract val layoutId: Int
    abstract fun initView(savedInstanceState: Bundle?)
    abstract val viewModel: T
    override fun onDestroy() {
        //弹出activity栈
        ActivityStackManager.activityList.remove(this)
        super.onDestroy()
    }

}