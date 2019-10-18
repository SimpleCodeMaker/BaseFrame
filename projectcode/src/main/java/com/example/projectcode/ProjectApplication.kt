package com.example.projectcode

import android.content.Context
import android.util.Log
import cn.jpush.android.api.JPushInterface
import cn.jpush.android.api.TagAliasCallback
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.casual.baseframe.base.BFApplication
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.hyphenate.chatview.DemoHelper
import com.hyphenate.easeui.EaseUI

class ProjectApplication : BFApplication() {
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = this
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
        JPushInterface.setDebugMode(true)    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this)            // 初始化 JPush
        JPushInterface.setAlias(
            this,0,
            "baseframe"
        )
DemoHelper.getInstance().init(this)

//        val options = EMOptions();
//// 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
//// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
//        options.setAutoTransferMessageAttachments(true);
//// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
//        options.setAutoDownloadThumbnail(true);
////初始化
//        EaseUI.getInstance().init(this, options);
////在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//        EMClient.getInstance().setDebugMode(true);
        
//        JPushInterface.setAliasAndTags(getApplicationContext(),
//            "baseframe",
//            null,
//            object : TagAliasCallback {
//                override fun gotResult(p0: Int, p1: String?, p2: MutableSet<String>?) {
//                    when (p0) {
//                        0 -> {
//                            Log.d("push", "success")
//                        }
//                        6002 -> {
//                            Log.d(
//                                "push",
//                                "Failed to set alias and tags due to timeout. Try again after 60s."
//                            )
//                        }
//                        else -> {
//                            Log.d("push", "Failed with errorCode = $p0")
//                        }
//                    }
//                }
//
//            });
    }


}