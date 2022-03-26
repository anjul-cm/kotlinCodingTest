/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/25/22, 11:00 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation

@Dao
interface StationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(station: ChargingStation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStationList(stationList: List<ChargingStation>)

    @Query("Select * from ChargingStation")
    fun getAllStationPagingSource(): PagingSource<Int, ChargingStation>

    @Query("Delete from ChargingStation")
    suspend fun deleteAll()


}