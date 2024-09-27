package com.example.login.di

import androidx.lifecycle.ViewModel
import com.example.base.di.ViewModelKey
import com.example.presentation.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LoginViewModelModule {
    @Provides
    @[IntoMap ViewModelKey(LoginViewModel::class)]
    fun provideLoginViewModel(viewModel: LoginViewModel): ViewModel = viewModel
}