package com.codingmountain.kotlincodingtest.ui.main.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codingmountain.kotlincodingtest.network.Resource
import com.codingmountain.kotlincodingtest.network.responses.stationresponse.ChargingStation
import com.codingmountain.kotlincodingtest.repo.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardActivityViewModel @Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _fetchChargingStationLiveData = MutableLiveData<Resource<List<ChargingStation>>>()
    val fetchChargingLiveData: LiveData<Resource<List<ChargingStation>>> get() = _fetchChargingStationLiveData

    val chargingStationFlow =
        mainRepository.getChargingStationFlowFromLocalDatabase().cachedIn(viewModelScope)

    val loggedInUserLiveData = mainRepository.loggedInUserLiveData

    fun logOut() {
        mainRepository.logOut()
    }

    fun fetchChargingStation() {
        _fetchChargingStationLiveData.value = Resource.Loading
        viewModelScope.launch {
            _fetchChargingStationLiveData.value = mainRepository.fetchAndStoreChargingStation()
        }
    }


}
