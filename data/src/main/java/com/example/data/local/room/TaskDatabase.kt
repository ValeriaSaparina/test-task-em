package com.example.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.room.dao.FavoriteVacanciesDao
import com.example.data.local.room.typeConverter.AddressTypeConverter
import com.example.data.local.room.typeConverter.ExperienceTypeConverter
import com.example.data.local.room.typeConverter.ListStringTypeConverter
import com.example.data.local.room.typeConverter.SalaryTypeConverter
import com.example.data.remote.response.VacancyDataModel

@Database(
    entities = [
        VacancyDataModel::class
    ],
    version = 1
)
@TypeConverters(
    AddressTypeConverter::class,
    ExperienceTypeConverter::class,
    ListStringTypeConverter::class,
    SalaryTypeConverter::class
)
abstract class TaskDatabase : RoomDatabase() {
    abstract val favoriteVacanciesDao: FavoriteVacanciesDao
}