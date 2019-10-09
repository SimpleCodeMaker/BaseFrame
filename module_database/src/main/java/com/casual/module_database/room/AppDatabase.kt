package com.casual.module_database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDataDao(): RoomDataDao

    companion object {
        private var dataBase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (dataBase == null) {
                synchronized(AppDatabase::class.java) {
                    if (dataBase == null) {
                        dataBase =
                            Room.databaseBuilder(context, AppDatabase::class.java, "BaseFrame.db")
                                .fallbackToDestructiveMigration() // 在数据库版本升级时，直接删除旧版数据
                                .allowMainThreadQueries() // 允许在主线程中操作
                                .build()
                    }
                }
            }
            return dataBase!!
        }
    }
}