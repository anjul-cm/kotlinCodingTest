package com.codingmountain.kotlincodingtest.ui.main.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codingmountain.kotlincodingtest.repo.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardActivityViewModel @Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    val chargingStationFlow = mainRepository.getChargingStationFlow().cachedIn(viewModelScope)

    val loggedInUserLiveData = mainRepository.loggedInUserLiveData

    fun logOut() {
        mainRepository.logOut()
    }


}
