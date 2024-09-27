package com.example.main.di

import com.example.data.di.scope.MyScope
import com.example.di.component.CoreComponent
import com.example.main.MainFragment
import com.example.presentation.factory.ViewModelModule
import dagger.Component

@MyScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelModule::class, MainViewModelModule::class],
)
interface MainComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): MainComponent
    }


    fun inject(loginFragment: MainFragment)
}