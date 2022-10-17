package com.example.tmovies.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmovies.data.database.entities.MoviesEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_table")
    suspend fun gelAllMovies(): List<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MoviesEntity>)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAllMovies()
}