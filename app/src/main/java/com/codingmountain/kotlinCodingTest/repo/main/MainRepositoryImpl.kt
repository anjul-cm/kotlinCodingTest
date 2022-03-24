package com.codingmountain.kotlincodingtest.repo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class MainRepositoryImpl
@Inject
constructor(
    private val firebaseAuth: FirebaseAuth
) : MainRepository {

    private val _loggedInUserLiveData = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val loggedInUserLiveData: LiveData<FirebaseUser?> get() = _loggedInUserLiveData

    override fun logOut() {
        firebaseAuth.signOut()
        _loggedInUserLiveData.value = firebaseAuth.currentUser
    }
}