package com.example.tmovies.di

import android.content.Context
import androidx.room.Room
import com.example.tmovies.data.database.MoviesDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val MOVIES_DATABASE_NAME = "movies_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MoviesDB::class.java, MOVIES_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMoviesDao(db: MoviesDB) = db.getMoviesDao()
}