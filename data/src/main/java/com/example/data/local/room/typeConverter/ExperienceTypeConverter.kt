package com.example.data.local.room.typeConverter

import androidx.room.TypeConverter
import com.example.data.remote.response.ExperienceResponse
import javax.inject.Inject

class ExperienceTypeConverter @Inject constructor() {

    @TypeConverter
    fun fromResponseToString(experience: ExperienceResponse): String {
        return with(experience) {
            "${previewText}, ${text}"
        }
    }

    @TypeConverter
    fun fromStringToResponse(experience: String): ExperienceResponse {
        val arr = experience.split(", ")
        return ExperienceResponse(previewText = arr[0], text = arr[1])
    }

}