package com.codingmountain.kotlincodingtest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation

@Dao
interface StationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(station: ChargingStation)

}