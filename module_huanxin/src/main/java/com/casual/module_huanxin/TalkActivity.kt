package com.casual.module_huanxin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyphenate.chatview.chatuidemo.ChatFragment
import com.hyphenate.easeui.EaseConstant




//@Route(path = MODULE_HUANXIN_MAIN)
class TalkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_talk)
        //new出EaseChatFragment或其子类的实例
        val chatFragment = ChatFragment()
        //传入参数
        val args = Bundle()
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE)
        args.putString(EaseConstant.EXTRA_USER_ID, "123")
        chatFragment.arguments = args
        supportFragmentManager.beginTransaction().add(R.id.container, chatFragment).commit()
    }
}
