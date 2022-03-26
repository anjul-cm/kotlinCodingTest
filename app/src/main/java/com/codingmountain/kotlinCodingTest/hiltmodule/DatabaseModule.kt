/*
 * Created by Diken Maharjan
 * dikenmaharjan10@gmail.com
 * Last modified 3/25/22, 10:15 PM
 * Copyright (c) 2022.
 * All rights reserved.
 */

package com.codingmountain.kotlincodingtest.hiltmodule

import android.content.Context
import androidx.room.Room
import com.codingmountain.kotlincodingtest.database.StationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun getDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, StationDatabase::class.java, StationDatabase.DATABASE_NAME)
        .build()

    @Provides
    fun getStationDao(database: StationDatabase) = database.getStationDao()
}