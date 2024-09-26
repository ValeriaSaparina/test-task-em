package com.example.data.di

import com.example.data.repository.OfferRepositoryImpl
import com.example.data.repository.VacancyRepositoryImpl
import com.example.domain.repository.OfferRepository
import com.example.domain.repository.VacancyRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModuleBinder {
    @Binds
    @Singleton
    fun bindVacancyRepository(repositoryImpl: VacancyRepositoryImpl): VacancyRepository

    @Binds
    @Singleton
    fun bindOfferRepository(repositoryImpl: OfferRepositoryImpl): OfferRepository
}