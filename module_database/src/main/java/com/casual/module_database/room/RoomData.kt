package com.casual.module_database.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.casual.baseframe.base.BFBean

@Entity
class RoomData:BFBean() {
    @PrimaryKey
    var dataId: Long = 0
    
    var dataContent:String = ""
}