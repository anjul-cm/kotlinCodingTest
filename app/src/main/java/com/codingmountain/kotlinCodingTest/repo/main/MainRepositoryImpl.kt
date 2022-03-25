package com.codingmountain.kotlincodingtest.repo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation
import com.codingmountain.kotlincodingtest.network.services.StationApi
import com.codingmountain.kotlincodingtest.ui.main.dashboard.paging.ChargingStationPagingSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MainRepositoryImpl
@Inject
constructor(
    private val firebaseAuth: FirebaseAuth,
    private val stationApi: StationApi
) : MainRepository {

    private val _loggedInUserLiveData = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val loggedInUserLiveData: LiveData<FirebaseUser?> get() = _loggedInUserLiveData

    override fun logOut() {
        firebaseAuth.signOut()
        _loggedInUserLiveData.value = firebaseAuth.currentUser
    }

    override fun getChargingStationFlow(): Flow<PagingData<ChargingStation>> {
        return Pager(
            PagingConfig(
                pageSize = 5, prefetchDistance = 5, enablePlaceholders = false
            )
        ) { ChargingStationPagingSource() }.flow
    }
}