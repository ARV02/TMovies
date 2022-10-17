package com.example.tmovies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmovies.data.database.dao.MoviesDao
import com.example.tmovies.data.database.entities.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviesDB: RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao
}