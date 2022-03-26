/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/25/22, 9:14 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.network.services

import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation
import retrofit2.Response
import retrofit2.http.GET


interface StationApi {

    @GET("anjul-cm/ea4d75e7d91af963a1acf3c6d57f1cd2/raw/7b98057d01f7dc4a576267b11d156c6c8dac9827/data.json")
    suspend fun getChargingStations(): Response<List<ChargingStation>>
}