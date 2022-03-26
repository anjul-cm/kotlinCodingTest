package com.codingmountain.kotlincodingtest.ui.main.dashboard

import androidx.lifecycle.*
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

    private val _hardCodedDataSourceLiveData = MutableLiveData(false)
    val hardCodedDataSourceLiveData: LiveData<Boolean> get() = _hardCodedDataSourceLiveData

    val chargingStationLiveData =
        Transformations.switchMap(hardCodedDataSourceLiveData) { shouldShowHardCodedData ->
            if (shouldShowHardCodedData) {
                mainRepository.getHardCodedChargingFlow().cachedIn(viewModelScope).asLiveData()
            } else {
                mainRepository.getChargingStationFlowFromLocalDatabase().cachedIn(viewModelScope)
                    .asLiveData()
            }
        }

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

    fun deleteAllData() {
        viewModelScope.launch {
            mainRepository.deleteAllData()
        }
    }

    fun changeDataSource() {
        _hardCodedDataSourceLiveData.value = !(hardCodedDataSourceLiveData.value ?: true)
    }


}
