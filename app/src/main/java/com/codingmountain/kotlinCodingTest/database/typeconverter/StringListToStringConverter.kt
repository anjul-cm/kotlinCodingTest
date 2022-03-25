package com.codingmountain.kotlincodingtest.database.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class StringListToStringConverter {
    @TypeConverter
    fun listToString(list: List<String>): String = Gson().toJson(list)

    @TypeConverter
    fun stringToList(string: String): List<String> =
        Gson().fromJson(string, object : TypeToken<List<String>>() {}.type)
}