package com.example.data.local.room.typeConverter

import androidx.room.TypeConverter
import com.example.data.remote.response.SalaryResponse
import javax.inject.Inject

class SalaryTypeConverter @Inject constructor() {
    @TypeConverter
    fun fromResponseToString(salary: SalaryResponse): String {
        var result = ""
        if (salary.full != null) {
            result += salary.full
        }
        result += "*&"
        if (salary.short != null) {
            result += salary.short
        }
        return result
    }

    @TypeConverter
    fun fromStringToResponse(salary: String): SalaryResponse {
        val arr = salary.split("*&")
        return SalaryResponse(short = arr[0], full = arr[1])
    }
}