package com.codingmountain.kotlincodingtest.hiltmodule

import com.codingmountain.kotlincodingtest.network.services.StationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun providesRetrofitInstance() =
        Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                getHttpClient()
            )
            .build()

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY )
            }
        ).build()
    }

    @Provides
    fun providesStationApi(retrofit: Retrofit) = retrofit.create(StationApi::class.java)
}