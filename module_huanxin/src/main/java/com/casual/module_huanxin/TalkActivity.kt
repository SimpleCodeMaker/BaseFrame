package com.casual.module_huanxin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.projectcode.RouteUris.Activitys.MODULE_HUANXIN_MAIN
import kotlinx.android.synthetic.main.activity_huan_xin.*
import com.hyphenate.chat.EMClient
import com.hyphenate.EMCallBack
import android.R.attr.password
import android.content.Intent
import android.util.Log
import com.hyphenate.easeui.ui.EaseChatFragment
import com.hyphenate.easeui.EaseConstant




//@Route(path = MODULE_HUANXIN_MAIN)
class TalkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_talk)
        //new出EaseChatFragment或其子类的实例
        val chatFragment = EaseChatFragment()
        //传入参数
        val args = Bundle()
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE)
        args.putString(EaseConstant.EXTRA_USER_ID, "123")
        chatFragment.arguments = args
        supportFragmentManager.beginTransaction().add(R.id.container, chatFragment).commit()
    }
}
