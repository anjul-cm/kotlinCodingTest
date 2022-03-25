package com.codingmountain.kotlincodingtest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.codingmountain.kotlincodingtest.repo.main.MainRepositoryImpl
import com.codingmountain.kotlincodingtest.ui.main.paging.ChargingStationPagingSource
import com.codingmountain.kotlincodingtest.ui.main.recyclerview.ChargingStation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardActivityViewModel @Inject
constructor(private val mainRepositoryImpl: MainRepositoryImpl) : ViewModel() {

    val chargingStationFlow = Pager<Int, ChargingStation>(
        PagingConfig(
            pageSize = 5, prefetchDistance = 5, enablePlaceholders = false
        )
    ) { ChargingStationPagingSource() }.flow.cachedIn(viewModelScope)


    val loggedInUserLiveData = mainRepositoryImpl.loggedInUserLiveData

    fun logOut() {
        mainRepositoryImpl.logOut()
    }


}
