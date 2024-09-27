package com.example.presentation.factory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {
    @Binds
    fun bindDaggerFactory_to_viewModelFacroty(impl: DaggerViewModelFactory): ViewModelProvider.Factory
}