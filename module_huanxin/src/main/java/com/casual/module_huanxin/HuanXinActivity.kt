package com.casual.module_huanxin

import android.Manifest
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
import com.hyphenate.chatview.DemoHelper
import com.hyphenate.chatview.runtimepermissions.PermissionsManager
import com.hyphenate.chatview.runtimepermissions.PermissionsResultAction
import com.tbruyelle.rxpermissions2.RxPermissions


@Route(path = MODULE_HUANXIN_MAIN)
class HuanXinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huan_xin)
//        

        PermissionsManager.getInstance()
            .requestAllManifestPermissionsIfNecessary(this, object : PermissionsResultAction() {
                override fun onGranted() {
                    
                }

                override fun onDenied(permission: String?) {
                    
                }

            })
        regist.setOnClickListener {

        }
        sign_in.setOnClickListener {
            EMClient.getInstance().login("1234", "1234", object : EMCallBack {
                //回调
                override fun onSuccess() {
                    // 将自己服务器返回的环信账号、昵称和头像URL设置到帮助类中。 这样聊天才会显示头像信息
//                    DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(userMsg.nickname);
//                    DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(userMsg.avatar);
//                    DemoHelper.getInstance().setCurrentUserName(userMsg.userId); // 环信Id
                    
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
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults)
    }
}
