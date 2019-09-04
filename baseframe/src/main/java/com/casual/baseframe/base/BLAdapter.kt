package com.casual.baseframe.base

import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.casual.baseframe.R

abstract class BLAdapter<T : MultiItemEntity>(data: List<T>? = null) :
    BaseMultiItemQuickAdapter<T, BaseViewHolder>(data) {

    override fun bindToRecyclerView(recyclerView: RecyclerView?) {
        super.bindToRecyclerView(recyclerView)
        //设置item入场动画
        openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)
        //设置空白页
        setEmptyView(R.layout.empty)

    }
}