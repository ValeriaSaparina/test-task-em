package com.example.data.remote

import com.example.data.remote.response.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApi {
    @GET("u/0/uc")
    suspend fun getData(
        @Query("id") id: String,
        @Query("export") export: String
    ): ResponseModel?
}
