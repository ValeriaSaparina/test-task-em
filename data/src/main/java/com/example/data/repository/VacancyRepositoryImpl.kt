package com.example.data.repository

import com.example.data.local.room.dao.FavoriteVacanciesDao
import com.example.data.mapper.DataDomainMapper
import com.example.data.remote.DataApi
import com.example.data.util.Constants
import com.example.domain.model.VacancyDomainModel
import com.example.domain.repository.VacancyRepository
import javax.inject.Inject

class VacancyRepositoryImpl @Inject constructor(
    private val dataApi: DataApi,
    private val favoriteVacanciesDao: FavoriteVacanciesDao,
    private val mapper: DataDomainMapper,
) : VacancyRepository {
    override suspend fun getVacancies(): List<VacancyDomainModel> {
        val result =
            dataApi.getData(
                Constants.API_ID,
                Constants.EXPORT_API
            )?.vacancies
                ?.map { vacancyDataModel ->
                    vacancyDataModel.toDomainModel()
                } ?: listOf()

        result.forEach { vacancy ->
            if (vacancy.isFavorite) {
                addAllFavoriteVacancies(vacancy)
            } else {
                deleteFavoriteVacancy(vacancy)
            }
        }
        return result
    }

    override suspend fun getVacancies(n: Int): List<VacancyDomainModel> {
        val result =
            dataApi.getData(
                Constants.API_ID,
                Constants.EXPORT_API
            )?.vacancies?.subList(0, 3)
                ?.map { vacancyDataModel ->
                    vacancyDataModel.toDomainModel()
                } ?: listOf()

        result.forEach { vacancy ->
            if (vacancy.isFavorite) {
                addAllFavoriteVacancies(vacancy)
            }
        }
        return result
    }

    override suspend fun getVacancyById(id: String): VacancyDomainModel {
        dataApi.getData(
            Constants.API_ID,
            Constants.EXPORT_API
        )?.vacancies?.forEach { vacancy ->
            if (vacancy.id == id) return vacancy.toDomainModel().copy(isFavorite = isFavVacancy(id))
        }
        return VacancyDomainModel.getDefaultValue()
    }

    private suspend fun isFavVacancy(id: String): Boolean {
        return favoriteVacanciesDao.getVacancyById(id) != null
    }

    override suspend fun addAllFavoriteVacancies(vararg vacancies: VacancyDomainModel) {
        favoriteVacanciesDao.addAll(
            vacancies = vacancies.map { vacancyDomainModel ->
                mapper.toDataModel(vacancyDomainModel)
            }.toTypedArray()
        )
    }

    override suspend fun addAllFavoriteVacancies(vararg ids: String) {
        ids.forEach { id ->
            addAllFavoriteVacancies(getVacancyById(id).copy(isFavorite = true))
        }
    }

    override suspend fun getAllFavoriteVacancies(): List<VacancyDomainModel> {
        return favoriteVacanciesDao.getAll()
            ?.map { vacancyDataModel -> vacancyDataModel.toDomainModel() } ?: listOf()
    }

    override suspend fun getFavoriteVacanciesNumber(): Int {
        return favoriteVacanciesDao.getVacanciesNumber()
    }

    override suspend fun getFavoriteVacancyById(id: String): VacancyDomainModel {
        return favoriteVacanciesDao.getVacancyById(id)?.toDomainModel()
            ?: VacancyDomainModel.getDefaultValue()
    }

    override suspend fun deleteFavoriteVacancy(vacancy: VacancyDomainModel) {
        favoriteVacanciesDao.delete(mapper.toDataModel(vacancy))
    }

    override suspend fun deleteFavoriteVacancy(vararg ids: String) {
        ids.forEach { id ->
            deleteFavoriteVacancy(getVacancyById(id))
        }
    }

}
