package com.example.relevantvacancies.di

import androidx.lifecycle.ViewModel
import com.example.base.di.ViewModelKey
import com.example.presentation.viewmodel.RelevantVacanciesViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class RelevantVacancyViewModelModule {
    @Provides
    @[IntoMap ViewModelKey(RelevantVacanciesViewModel::class)]
    fun provideRelevantVacancyViewModel(viewModel: RelevantVacanciesViewModel): ViewModel =
        viewModel
}