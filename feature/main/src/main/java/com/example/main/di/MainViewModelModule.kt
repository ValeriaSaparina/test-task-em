package com.example.main.di

import androidx.lifecycle.ViewModel
import com.example.base.di.ViewModelKey
import com.example.presentation.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MainViewModelModule {
    @Provides
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(viewModel: MainViewModel): ViewModel = viewModel
}