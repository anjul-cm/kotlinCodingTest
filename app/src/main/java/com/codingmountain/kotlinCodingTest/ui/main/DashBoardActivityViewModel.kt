package com.codingmountain.kotlincodingtest.ui.main

import androidx.lifecycle.ViewModel
import com.codingmountain.kotlincodingtest.repo.main.MainRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardActivityViewModel @Inject
constructor(private val mainRepositoryImpl: MainRepositoryImpl) : ViewModel() {

    val loggedInUserLiveData = mainRepositoryImpl.loggedInUserLiveData

    fun logOut() {
        mainRepositoryImpl.logOut()
    }
}
