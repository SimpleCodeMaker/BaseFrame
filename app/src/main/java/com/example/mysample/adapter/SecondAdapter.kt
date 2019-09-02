package com.example.mysample.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.example.baselibrary.base.BLAdapter
import com.example.mysample.R
import com.example.mysample.model.DataX

class SecondAdapter(data: List<DataX>? = null) :
    BLAdapter<DataX>(data) {
    init {
        addItemType(0, R.layout.item)
    }

    override fun convert(helper: BaseViewHolder, item: DataX) {
        helper.setText(R.id.title, item.title)
    }
}