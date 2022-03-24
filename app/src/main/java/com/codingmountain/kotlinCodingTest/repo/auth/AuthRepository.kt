package com.codingmountain.kotlincodingtest.repo.auth

import com.codingmountain.kotlincodingtest.network.Resource
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    fun signUp(email: String, password: String, status: (Resource<AuthResult>) -> Unit)
    fun signIn(email: String, password: String, status: (Resource<AuthResult>) -> Unit)
}