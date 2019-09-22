package com.casual.baseframe.base

import com.chad.library.adapter.base.entity.MultiItemEntity

abstract class BFBean : MultiItemEntity {
    var multiItemType: Int = 0
    override fun getItemType(): Int = multiItemType
}