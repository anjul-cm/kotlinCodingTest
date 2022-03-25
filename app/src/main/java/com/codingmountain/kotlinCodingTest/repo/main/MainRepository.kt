package com.codingmountain.kotlincodingtest.repo.main

import androidx.paging.PagingData
import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun logOut()
    fun getChargingStationFlow(): Flow<PagingData<ChargingStation>>
}