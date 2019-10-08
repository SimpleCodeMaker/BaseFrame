package com.casual.mysample.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.casual.baseframe.base.BFActivity
import com.casual.mysample.R
import com.casual.mysample.vm.WelcomeVM
import com.example.projectcode.RouteUris
import com.example.projectcode.doRoute
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeActivity : BFActivity<WelcomeVM>() {
    override val layoutId: Int = R.layout.activity_welcome

    override fun initView(savedInstanceState: Bundle?) {
        GlobalScope.launch {
            delay(1500)
            RouteUris.Activitys.MODULE_MAIN_MAIN.doRoute(this@WelcomeActivity)
//            finish()
        }
    }

    override val viewModel: WelcomeVM by lazy {
        ViewModelProviders.of(this).get(WelcomeVM::class.java)
    }
}