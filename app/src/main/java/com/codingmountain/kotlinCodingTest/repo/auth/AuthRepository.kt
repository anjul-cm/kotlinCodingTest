package com.codingmountain.kotlincodingtest.repo.auth

import com.codingmountain.kotlincodingtest.network.Resource
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    private val firebaseAuth: FirebaseAuth
) {

    fun signUp(email: String, password: String, status: (Resource<AuthResult>) -> Unit) {
        status(Resource.Loading)
        try {
            if (emailValid(email, status) && passwordValid(password, status)) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getSignUpCompleteListener(status))
            }
        } catch (e: IllegalArgumentException) {
            status(getFailureResource(e, "Invalid Input"))
        } catch (e: Exception) {
            status(getFailureResource(e, "Something went wrong"))
        }
    }


    fun signIn(email: String, password: String, status: (Resource<AuthResult>) -> Unit) {
        status(Resource.Loading)
        try {
            if (emailValid(email, status) && passwordValid(password, status)) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getLoginCompleteListener(status))
            }
        } catch (e: IllegalArgumentException) {
            status(getFailureResource(e, "Invalid Input"))
        } catch (e: Exception) {
            status(getFailureResource(e, "Something went wrong."))
        }
    }


    //Validation Left
    private fun passwordValid(password: String, status: (Resource<AuthResult>) -> Unit): Boolean {
        return if (password.isBlank()) {
            status(getFailureResource(null, "Password cannot be empty."))
            false
        } else {
            true
        }
    }


    //Validation Left
    private fun emailValid(email: String, status: (Resource<AuthResult>) -> Unit): Boolean {
        return if (email.isBlank()) {
            status(getFailureResource(null, "Email cannot be empty."))
            false
        } else {
            true
        }
    }

    private fun getSignUpCompleteListener(status: (Resource<AuthResult>) -> Unit) =
        OnCompleteListener<AuthResult> { signUpTask ->
            if (signUpTask.isSuccessful) {
                status(Resource.Success(signUpTask.result))
            } else {
                status(getFailureResource(signUpTask.exception, "Something went wrong."))
            }
        }


    private fun getFailureResource(e: Exception?, msg: String): Resource.Failure {
        return Resource.Failure(e?.message ?: msg)
    }


    private fun getLoginCompleteListener(status: (Resource<AuthResult>) -> Unit) =
        OnCompleteListener<AuthResult> { logInTask ->
            if (logInTask.isSuccessful) {
                status(Resource.Success(logInTask.result))
            } else {
                status(getFailureResource(logInTask.exception, "Something went wrong."))
            }
        }

    fun resetPassword(email: String, status: (Resource<Unit>) -> Unit) {
        firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener {
            status(Resource.Success(Unit))
        }.addOnFailureListener { exception ->
            status(getFailureResource(exception, "Something went wrong."))
        }

    }

}