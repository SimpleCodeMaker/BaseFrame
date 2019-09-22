package com.casual.baseframe.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

/**
 * 只提供一些公有的东西，特性的东西 需要再生成一个派生类
 */
abstract class BFModel : ViewModel() {
    //提供默认的协程
    protected val presenterScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + SupervisorJob())
    }

    //页面销毁的时候父协程取消  子协程也会跟着取消
    override fun onCleared() {
        presenterScope.cancel()
        super.onCleared()
    }
}