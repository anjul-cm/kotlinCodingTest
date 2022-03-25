package com.codingmountain.kotlincodingtest.ui.main.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codingmountain.kotlincodingtest.repo.main.MainRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardActivityViewModel @Inject
constructor(private val mainRepositoryImpl: MainRepositoryImpl) : ViewModel() {

    val chargingStationFlow = mainRepositoryImpl.getChargingStationFlow().cachedIn(viewModelScope)

    val loggedInUserLiveData = mainRepositoryImpl.loggedInUserLiveData

    fun logOut() {
        mainRepositoryImpl.logOut()
    }


}
