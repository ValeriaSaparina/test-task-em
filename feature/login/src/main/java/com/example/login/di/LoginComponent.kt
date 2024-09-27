package com.example.login.di

import com.example.data.di.scope.MyScope
import com.example.di.component.CoreComponent
import com.example.login.LoginFragment
import com.example.presentation.factory.ViewModelModule
import dagger.Component

@MyScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelModule::class, LoginViewModelModule::class],
)
interface LoginComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): LoginComponent
    }


    fun inject(loginFragment: LoginFragment)
}