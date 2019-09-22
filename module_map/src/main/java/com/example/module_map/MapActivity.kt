package com.example.module_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.projectcode.RouteUris.Activitys.MODULE_MAP_MAIN

@Route(path = MODULE_MAP_MAIN)
class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
    }
}