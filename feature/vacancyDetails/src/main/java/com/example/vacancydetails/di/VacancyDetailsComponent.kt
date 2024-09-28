package com.example.vacancydetails.di

import com.example.data.di.scope.MyScope
import com.example.di.component.CoreComponent
import com.example.presentation.factory.ViewModelModule
import com.example.vacancydetails.VacancyDetailsFragment
import dagger.Component

@MyScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelModule::class, VacancyDetailsViewModelModule::class],
)
interface VacancyDetailsComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): VacancyDetailsComponent
    }


    fun inject(vacancyDetailsFragment: VacancyDetailsFragment)
}