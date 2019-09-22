package com.example.module_main.adapter

import com.casual.baseframe.base.BFAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.module_main.R
import com.example.projectcode.bean.DataX

class SecondAdapter(data: List<DataX>? = null) :
    BFAdapter<DataX>(data) {
    init {
        addItemType(0, R.layout.item)
    }

    override fun convert(helper: BaseViewHolder, item: DataX) {
        helper.setText(R.id.title, item.title)
    }
}