package com.example.data.local.room.typeConverter

import androidx.room.TypeConverter
import com.example.data.remote.response.AddressResponse
import javax.inject.Inject

class AddressTypeConverter @Inject constructor() {

    @TypeConverter
    fun fromResponseToString(address: AddressResponse): String {
        return with(address) {
            "${town}, ${street}, ${house}"
        }
    }

    @TypeConverter
    fun fromStringToResponse(address: String): AddressResponse {
        val arr = address.replace("[", "").replace("]", "").split(", ")
        return AddressResponse(town = arr[0], street = arr[1], house = arr[2])
    }

}