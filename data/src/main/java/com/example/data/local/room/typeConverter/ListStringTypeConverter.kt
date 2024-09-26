package com.example.data.local.room.typeConverter

import androidx.room.TypeConverter
import javax.inject.Inject

class ListStringTypeConverter @Inject constructor() {
    private val divider = "*&"

    @TypeConverter
    fun fromListToString(stringList: List<String>): String {
        var result = ""
        stringList.forEach { item ->
            result += item + divider
        }
        return result.substring(0, result.length - 2)
    }

    @TypeConverter
    fun fromStringToList(str: String): List<String> {
        val result = str.replace("[", "").replace("]", "").split("?,").map { item ->
            item.plus("?")
        }
        result.last().replace("??", "?")
        return result
    }
}