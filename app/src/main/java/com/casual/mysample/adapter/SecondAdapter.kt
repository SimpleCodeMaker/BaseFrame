package com.casual.mysample.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.casual.baseframe.base.BLAdapter
import com.casual.mysample.R
import com.casual.mysample.model.DataX

class SecondAdapter(data: List<DataX>? = null) :
    BLAdapter<DataX>(data) {
    init {
        addItemType(0, R.layout.item)
    }

    override fun convert(helper: BaseViewHolder, item: DataX) {
        helper.setText(R.id.title, item.title)
    }
}