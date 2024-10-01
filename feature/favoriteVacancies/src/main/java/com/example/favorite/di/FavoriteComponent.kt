package com.example.favorite.di

import com.example.data.di.scope.MyScope
import com.example.di.component.CoreComponent
import com.example.favorite.FavoriteFragment
import com.example.presentation.factory.ViewModelModule
import dagger.Component

@MyScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelModule::class, FavoriteViewModelModule::class],
)
interface FavoriteComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): FavoriteComponent
    }


    fun inject(favoriteFragment: FavoriteFragment)
}