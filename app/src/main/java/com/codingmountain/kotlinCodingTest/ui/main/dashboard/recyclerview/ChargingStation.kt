package com.codingmountain.kotlincodingtest.ui.main.dashboard.recyclerview

data class ChargingStation(
    val address: String,
    val city: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val province: String,
    val telephone: String,
    val type: List<String>
)