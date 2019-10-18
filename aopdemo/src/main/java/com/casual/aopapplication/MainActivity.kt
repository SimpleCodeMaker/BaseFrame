package com.casual.aopapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.module_main.aop.CheckLogin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        aop.setOnClickListener {
//            test()
        }
    }
//    @CheckLogin
//    private fun test() {
//        Toast.makeText(this@MainActivity,"跳转成功", Toast.LENGTH_SHORT).show()
//    }
}
