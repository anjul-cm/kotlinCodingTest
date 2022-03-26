/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/25/22, 10:45 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingmountain.kotlincodingtest.network.Resource
import com.codingmountain.kotlincodingtest.repo.auth.AuthRepository
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthActivityViewModel
@Inject
constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authenticationLiveData = MutableLiveData<Resource<AuthResult>>()
    val authenticationLiveData: LiveData<Resource<AuthResult>> get() = _authenticationLiveData

    fun logIn(email: String, password: String) {
        authRepository.signIn(email, password) { _authenticationLiveData.value = it }
    }

    fun signUp(email: String, password: String) {
        authRepository.signUp(email, password) { _authenticationLiveData.value = it }
    }

}