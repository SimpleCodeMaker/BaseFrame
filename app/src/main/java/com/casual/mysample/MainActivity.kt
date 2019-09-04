package com.casual.mysample

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.casual.baseframe.base.BLActivity
import com.casual.mysample.present.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BLActivity<MainViewModel>() {
    override val layoutId: Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
//        viewModel.list?.observe(this, Observer {
//            commit.text = it.get(0).name
//        })
//        commit.setOnClickListener {
//            viewModel.geta()
//        }
        content.setOnClickListener {
            startActivity(Intent(this,SecondActivity::class.java))
//            finish()
        }
    }

    override val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
}