package com.codingmountain.kotlincodingtest.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingmountain.kotlincodingtest.network.Resource
import com.codingmountain.kotlincodingtest.repo.auth.AuthRepositoryImpl
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthActivityViewModel
@Inject
constructor(
    private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {

    private val _authenticationLiveData = MutableLiveData<Resource<AuthResult>>()
    val authenticationLiveData: LiveData<Resource<AuthResult>> get() = _authenticationLiveData

    fun logIn(email: String, password: String) {
        authRepositoryImpl.signIn(email, password) { _authenticationLiveData.value = it }
    }

    fun signUp(email: String, password: String) {
        authRepositoryImpl.signUp(email, password) { _authenticationLiveData.value = it }
    }

}