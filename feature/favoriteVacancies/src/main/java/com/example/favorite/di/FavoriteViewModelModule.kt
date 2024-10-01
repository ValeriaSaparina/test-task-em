package com.example.favorite.di

import androidx.lifecycle.ViewModel
import com.example.base.di.ViewModelKey
import com.example.presentation.viewmodel.FavoriteViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class FavoriteViewModelModule {
    @Provides
    @[IntoMap ViewModelKey(FavoriteViewModel::class)]
    fun provideFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel = viewModel
}