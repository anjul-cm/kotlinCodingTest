package com.codingmountain.kotlincodingtest.network

sealed class Resource<out T> {
    class Success<T>(val value: T) : Resource<T>()
    class Failure(val errorMsg: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}