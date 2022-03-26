package com.codingmountain.kotlincodingtest.network.responses.stationresponse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.codingmountain.kotlincodingtest.database.typeconverter.StringListToStringConverter

@Entity(primaryKeys = ["latitude", "longitude"])
@TypeConverters(StringListToStringConverter::class)
data class ChargingStation(
    val address: String,
    val city: String,
    @ColumnInfo(name = "latitude")
    val latitude: String,

    @ColumnInfo(name = "longitude")
    val longitude: String,
    val name: String,
    val province: String,

    val telephone: String,
    val type: List<String>
)