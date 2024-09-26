package com.example.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.remote.response.VacancyDataModel

@Dao
interface FavoriteVacanciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(vararg vacancies: VacancyDataModel)

    @Query("SELECT * FROM favorite_vacancies")
    suspend fun getAll(): List<VacancyDataModel>?

    @Query("select count(*) from favorite_vacancies")
    suspend fun getVacanciesNumber(): Int

    @Query("select * from favorite_vacancies where id = :id")
    suspend fun getVacancyById(id: String): VacancyDataModel?

    @Delete
    suspend fun delete(vacancy: VacancyDataModel)
}
