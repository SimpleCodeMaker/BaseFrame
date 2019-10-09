package com.casual.module_database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDataDao {
    
    @Insert
    fun addItem(item:RoomData):Long



    @Query("SELECT * FROM RoomData")
    fun getAll(): List<RoomData>

    @Query("SELECT * FROM RoomData")
    fun getAllByLiveData(): LiveData<List<RoomData>>
    
    @Query("DELETE FROM RoomData")
    fun deleteALL()


}