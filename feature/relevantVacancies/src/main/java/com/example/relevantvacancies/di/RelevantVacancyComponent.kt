package com.example.relevantvacancies.di

import com.example.data.di.scope.MyScope
import com.example.di.component.CoreComponent
import com.example.presentation.factory.ViewModelModule
import com.example.relevantvacancies.RelevantVacanciesFragment
import dagger.Component

@MyScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelModule::class, RelevantVacancyViewModelModule::class],
)
interface RelevantVacancyComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): RelevantVacancyComponent
    }


    fun inject(relevantVacanciesFragment: RelevantVacanciesFragment)
}