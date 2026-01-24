package com.example.start_wars.di

import android.content.Context
import android.content.res.Resources
import com.example.start_wars.data.StarWarsDatabase
import com.example.start_wars.data.dao.FilmsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideResources(@ApplicationContext context: Context): Resources{
        return context.resources
    }

    @Provides
    @Singleton
    fun provideStarWarsDatabase(@ApplicationContext context: Context): StarWarsDatabase{
        return StarWarsDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providePlanetDao(starWarsDatabase: StarWarsDatabase): FilmsDao{
        return starWarsDatabase.getFilmsDao()
    }
}