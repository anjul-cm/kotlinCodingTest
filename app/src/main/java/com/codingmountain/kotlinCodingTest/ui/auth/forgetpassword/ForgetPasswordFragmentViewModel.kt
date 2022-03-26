package com.codingmountain.kotlincodingtest.ui.auth.forgetpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingmountain.kotlincodingtest.network.Resource
import com.codingmountain.kotlincodingtest.repo.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordFragmentViewModel
@Inject
constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _forgetPasswordStatusLiveData = MutableLiveData<Resource<Unit>>()
    val forgetPasswordStatusLiveData: LiveData<Resource<Unit>> get() = _forgetPasswordStatusLiveData

    fun resetPassword(email: String) {
        _forgetPasswordStatusLiveData.value = Resource.Loading
        authRepository.resetPassword(email) { _forgetPasswordStatusLiveData.value = it }
    }


}