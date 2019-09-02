package com.example.baselibrary.base

import com.chad.library.adapter.base.entity.MultiItemEntity

abstract class BLBean : MultiItemEntity {
    var multiItemType: Int = 0
    override fun getItemType(): Int = multiItemType
}