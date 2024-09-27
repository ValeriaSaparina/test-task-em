package com.example.login2.di

import com.example.data.di.scope.MyScope
import com.example.di.component.CoreComponent
import com.example.login.di.Login2ViewModelModule
import com.example.login2.Login2Fragment
import com.example.presentation.factory.ViewModelModule
import dagger.Component

@MyScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelModule::class, Login2ViewModelModule::class],
)
interface Login2Component {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): Login2Component
    }


    fun inject(login2Fragment: Login2Fragment)
}