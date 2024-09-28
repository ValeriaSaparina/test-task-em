package com.example.vacancydetails.di

import androidx.lifecycle.ViewModel
import com.example.base.di.ViewModelKey
import com.example.presentation.viewmodel.VacancyDetailsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class VacancyDetailsViewModelModule {
    @Provides
    @[IntoMap ViewModelKey(VacancyDetailsViewModel::class)]
    fun provideVacancyDetailsViewModel(viewModel: VacancyDetailsViewModel): ViewModel = viewModel
}