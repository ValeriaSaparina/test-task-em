package com.example.di.module

import android.content.Context
import androidx.room.Room
import com.example.data.local.room.TaskDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provide(ctx: Context): TaskDatabase =
        Room.databaseBuilder(ctx, TaskDatabase::class.java, "task.db").build()

    @Singleton
    @Provides
    fun provideFavoriteVacanciesDao(db: TaskDatabase) = db.favoriteVacanciesDao

}