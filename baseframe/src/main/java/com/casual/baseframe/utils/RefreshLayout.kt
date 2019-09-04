package com.casual.baseframe.utils

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

class RefreshLayout : SmartRefreshLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    var coroutine:CoroutineScope?=null
    var pageSize: Int = 10
    var pageIndex: Int = 1
    var isRefreshState = true
    var previousIndex:Int = 1
    fun refreshLoadMore(init:RefreshLoadMore.()->Unit){
        init()
        coroutine = CoroutineScope(Dispatchers.Main+ Job())
        setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                Log.d("setOnLoadMoreListener", "加载加载加载加载加载加载加载加载")
                isRefreshState=false
                loadMore?.invoke(++pageIndex)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                Log.d("setOnLoadMoreListener", "刷新刷新刷新刷新刷新刷新刷新刷新")
                isRefreshState=true
                refresh?.invoke()
            }

        })
    }
    companion object RefreshLoadMore{
        var refresh:(()->Unit)?=null
        var loadMore:((Int)->Unit)?=null
    }

    override fun onDetachedFromWindow() {
        coroutine?.cancel()
        super.onDetachedFromWindow()
    }
}