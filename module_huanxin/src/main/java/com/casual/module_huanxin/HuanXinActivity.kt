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


@Route(path = MODULE_HUANXIN_MAIN)
class HuanXinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huan_xin)


        regist.setOnClickListener {

        }
        sign_in.setOnClickListener {
            EMClient.getInstance().login("1234", "1234", object : EMCallBack {
                //回调
                override fun onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups()
                    EMClient.getInstance().chatManager().loadAllConversations()
                    Log.d("main", "登录聊天服务器成功！")
                }

                override fun onProgress(progress: Int, status: String) {

                }

                override fun onError(code: Int, message: String) {
                    Log.d("main", "登录聊天服务器失败！")
                }
            })
        }
        sign_out.setOnClickListener {

        }
        talk.setOnClickListener {
            startActivity(Intent(this, TalkActivity::class.java))
        }
    }
}
