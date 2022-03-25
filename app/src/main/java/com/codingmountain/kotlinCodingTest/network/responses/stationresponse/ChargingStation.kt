package com.codingmountain.kotlincodingtest.network.responses.stationresponse

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.codingmountain.kotlincodingtest.database.typeconverter.StringListToStringConverter

@Entity
@TypeConverters(StringListToStringConverter::class)
data class ChargingStation(
    val address: String,
    val city: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val province: String,

    @PrimaryKey(autoGenerate = false)
    val telephone: String,
    val type: List<String>
)