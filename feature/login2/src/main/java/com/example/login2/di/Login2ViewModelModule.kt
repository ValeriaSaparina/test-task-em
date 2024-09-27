package com.example.login.di

import androidx.lifecycle.ViewModel
import com.example.base.di.ViewModelKey
import com.example.presentation.viewmodel.Login2ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class Login2ViewModelModule {
    @Provides
    @[IntoMap ViewModelKey(Login2ViewModel::class)]
    fun provideLogin2ViewModel(viewModel: Login2ViewModel): ViewModel = viewModel
}