package com.example.data.di

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DataModuleBinder::class,
    ]
)
class DataModule