package com.example.di.component

import android.content.Context
import com.example.data.di.DataModule
import com.example.di.module.CoreModule
import com.example.di.module.DatabaseModule
import com.example.domain.repository.OfferRepository
import com.example.domain.repository.VacancyRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, DataModule::class, DatabaseModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    val vacancyRepository: VacancyRepository
    val offersRepository: OfferRepository

}