package com.nik.mycar.di

import android.content.Context
import androidx.room.Room
import com.nik.mycar.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase").build()

    @Singleton
    @Provides
    fun provideCarDao(
        database: AppDatabase
    ) = database.carDao()

    @Singleton
    @Provides
    fun provideCheckpointDao(
        database: AppDatabase
    ) = database.checkpointDao()

    @Singleton
    @Provides
    fun provideFuellingDao(
        database: AppDatabase
    ) = database.fuellingDao()

    @Singleton
    @Provides
    fun provideServiceDao(
        database: AppDatabase
    ) = database.serviceDao()
}
