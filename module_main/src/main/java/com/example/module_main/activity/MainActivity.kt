package com.example.module_main.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.casual.baseframe.base.BFActivity
import com.example.module_main.R
import com.example.module_main.vm.MainViewModel
import com.example.projectcode.RouteUris
import com.example.projectcode.doRoute
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouteUris.Activitys.MODULE_MAIN_MAIN)
class MainActivity : BFActivity<MainViewModel>() {
    override val layoutId: Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        map.setOnClickListener {
            RouteUris.Activitys.MODULE_MAP_MAIN.doRoute(this)
        }
//        viewModel.list?.observe(this, Observer {
//            content.text = it.get(0).name
//        })
//
//        commit.setOnClickListener {
//            var arr = arrayOf(246, -100, 6, 5)
//            //冒泡
////            for (i in 0 until arr.size-1) {
////                for (j in 0 until arr.size - i-1) {
////                    if (arr[j] > arr[j + 1]) {
////                        val temp = arr[j];
////                        arr[j] = arr[j + 1];
////                        arr[j + 1] = temp;
////                    }
////                }
////            }
//            Log.d("sdf", "bbbb" + arr.toString())
//        }
//
//        //点击跳下页
//        content.setOnClickListener {
//            startActivity(Intent(this, SecondActivity::class.java))
////            finish()
//        }
    }

    override val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
}