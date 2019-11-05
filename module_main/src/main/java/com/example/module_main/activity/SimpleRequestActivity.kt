package com.example.module_main.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.casual.baseframe.base.BFActivity
import com.example.module_main.R
import com.example.module_main.aop.CheckLogin
import com.example.module_main.vm.MainViewModel
import com.example.projectcode.RouteUris
import com.example.projectcode.doRoute
import kotlinx.android.synthetic.main.activity_simplerequest.*

@Route(path = RouteUris.Activitys.MODULE_MAIN_MAIN)
class SimpleRequestActivity : BFActivity<MainViewModel>() {
    override val layoutId: Int = R.layout.activity_simplerequest

    override fun initView(savedInstanceState: Bundle?) {
//        map.setOnClickListener {
//            RouteUris.Activitys.MODULE_MAP_MAIN.doRoute(this)
////            startActivity(Intent(this, SecondActivity::class.java))
//
//        }
//        huanxin.setOnClickListener {
//            RouteUris.Activitys.MODULE_HUANXIN_MAIN.doRoute(this)
//        }
//        database.setOnClickListener {
//            RouteUris.Activitys.MODULE_DATABASE_MAIN.doRoute(this)
//        }
//        aop.setOnClickListener {
////            test()
//        }
        viewModel.list?.observe(this, Observer {
            content.text = it.get(0).name
        })

        request.setOnClickListener {
            viewModel.getdata()
        }
//
//        //点击跳下页
        refresh.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
//            finish()
        }
    }

    @CheckLogin
    private fun test() {
        Toast.makeText(this@SimpleRequestActivity, "跳转成功", Toast.LENGTH_SHORT).show()
    }

    override val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
}