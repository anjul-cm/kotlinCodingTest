package com.codingmountain.kotlincodingtest.network.responses.stationresponse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChargingStation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String,
    val city: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val province: String,
    val telephone: String,
    val type: List<String>
)