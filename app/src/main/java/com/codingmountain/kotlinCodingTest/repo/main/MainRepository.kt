package com.codingmountain.kotlincodingtest.repo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codingmountain.kotlincodingtest.database.dao.StationDao
import com.codingmountain.kotlincodingtest.network.Resource
import com.codingmountain.kotlincodingtest.network.SafeApiCall
import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation
import com.codingmountain.kotlincodingtest.network.services.StationApi
import com.codingmountain.kotlincodingtest.ui.main.dashboard.paging.ChargingStationPagingSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MainRepository
@Inject
constructor(
    private val firebaseAuth: FirebaseAuth,
    private val stationApi: StationApi,
    private val stationDao: StationDao
) {

    private val _loggedInUserLiveData = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val loggedInUserLiveData: LiveData<FirebaseUser?> get() = _loggedInUserLiveData

    fun logOut() {
        firebaseAuth.signOut()
        _loggedInUserLiveData.value = firebaseAuth.currentUser
    }

    fun getChargingStationFlow(): Flow<PagingData<ChargingStation>> {
        return Pager(getPagingConfig()) {
            ChargingStationPagingSource()
        }.flow
    }

    fun getChargingStationFlowFromLocalDatabase(): Flow<PagingData<ChargingStation>> {
        return Pager(
            getPagingConfig()
        ) { stationDao.getAllStationPagingSource() }.flow
    }

    private fun getPagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 5, prefetchDistance = 5, enablePlaceholders = false
        )
    }

    suspend fun fetchAndStoreChargingStation(): Resource<List<ChargingStation>> {
        val response = SafeApiCall.execute { stationApi.getChargingStations() }
        when (response) {
            is Resource.Failure -> {} //These cases will be handled by UI observing the fetch livedata.
            Resource.Loading -> {}
            is Resource.Success -> {
                stationDao.insertStationList(response.value)
            }
        }
        return response
    }

    suspend fun deleteAllData() {
        stationDao.deleteAll()
    }
}