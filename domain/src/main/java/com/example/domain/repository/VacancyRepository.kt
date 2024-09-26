package com.example.domain.repository

import com.example.domain.model.VacancyDomainModel

interface VacancyRepository {

    suspend fun getVacancies(): List<VacancyDomainModel>
    suspend fun getVacancies(n: Int): List<VacancyDomainModel>
    suspend fun getVacancyById(id: String): VacancyDomainModel

    suspend fun addAllFavoriteVacancies(vararg vacancies: VacancyDomainModel)
    suspend fun addAllFavoriteVacancies(vararg ids: String)
    suspend fun getAllFavoriteVacancies(): List<VacancyDomainModel>
    suspend fun getFavoriteVacanciesNumber(): Int
    suspend fun getFavoriteVacancyById(id: String): VacancyDomainModel
    suspend fun deleteFavoriteVacancy(vacancy: VacancyDomainModel)
    suspend fun deleteFavoriteVacancy(vararg ids: String)

}