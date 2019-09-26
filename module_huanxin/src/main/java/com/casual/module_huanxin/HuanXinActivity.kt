package com.casual.module_huanxin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.projectcode.RouteUris.Activitys.MODULE_HUANXIN_MAIN
@Route(path = MODULE_HUANXIN_MAIN)
class HuanXinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huan_xin)
    }
}
