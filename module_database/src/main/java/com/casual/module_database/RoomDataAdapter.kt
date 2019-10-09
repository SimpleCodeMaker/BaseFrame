package com.casual.module_database

import com.casual.baseframe.base.BFAdapter
import com.casual.module_database.room.RoomData
import com.chad.library.adapter.base.BaseViewHolder

class RoomDataAdapter : BFAdapter<RoomData>() {
    init {
        addItemType(0, R.layout.item_room_data)
    }

    override fun convert(helper: BaseViewHolder, item: RoomData?) {
        helper.setText(R.id.index, item?.dataId?.toString())
            .setText(R.id.content, item?.dataContent)
    }
}