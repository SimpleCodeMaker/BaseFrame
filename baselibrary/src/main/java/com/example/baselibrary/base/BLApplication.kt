package com.example.baselibrary.base

import android.app.Application
import com.example.baselibrary.utils.Utils
import com.scwang.smartrefresh.layout.SmartRefreshLayout

abstract class BLApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.apply {
                autoLoadMore()
            }
        }
    }
}