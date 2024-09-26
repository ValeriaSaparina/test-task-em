package com.example.di.module

import android.app.Application
import com.example.di.TestApp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: TestApp): Application

}