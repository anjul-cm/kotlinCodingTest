/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/25/22, 10:26 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingmountain.kotlincodingtest.database.dao.StationDao
import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation

@Database(entities = [ChargingStation::class], version = 1, exportSchema = false)
abstract class StationDatabase : RoomDatabase() {
    abstract fun getStationDao(): StationDao

    companion object {
        const val DATABASE_NAME = "StationDatabase"
    }
}