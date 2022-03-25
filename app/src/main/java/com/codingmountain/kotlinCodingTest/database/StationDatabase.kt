package com.codingmountain.kotlincodingtest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingmountain.kotlincodingtest.database.dao.StationDao

@Database(entities = [StationDatabase::class], version = 1)
abstract class StationDatabase : RoomDatabase() {
    abstract fun getStationDao(): StationDao
}