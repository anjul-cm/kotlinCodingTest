package com.codingmountain.kotlincodingtest.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

object SafeApiCall {
    suspend fun <T> execute(networkCall: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = networkCall.invoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        return@withContext Resource.Success(it)
                    }
                }
                Resource.Failure("Something went wrong.")
            } catch (e: IOException) {
                e.printStackTrace()
                Resource.Failure("Cannot connect to server.")
            } catch (e: HttpException) {
                e.printStackTrace()
                Resource.Failure("Server error.")
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Failure("Something went wrong.")
            }
        }

    }
}