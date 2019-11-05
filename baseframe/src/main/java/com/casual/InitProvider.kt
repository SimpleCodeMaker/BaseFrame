package com.casual

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.casual.baseframe.BuildConfig
import com.casual.baseframe.utils.Utils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.squareup.leakcanary.LeakCanary
import com.tencent.bugly.crashreport.CrashReport

/**
 *用contentprovider初始化框架默认工具  那样这个框架使用的时候就不需要重新建立一个application
 */
class InitBaseFrameProvider : ContentProvider() {
    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        return null
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        return null
    }

    /**
     * 初始化
     */
    override fun onCreate(): Boolean {
        Utils.init(context!!)
        CrashReport.initCrashReport(context!!, "b2cce9e661", BuildConfig.DEBUG);
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.apply {
                autoLoadMore()
            }
        }
        return true
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun getType(p0: Uri): String? {
        return null
    }
}