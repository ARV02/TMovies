package com.example.tmovies.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tmovies.domain.model.Movie

@Entity(tableName = "movies_table")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "movie_id")val movieId: Int,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "poster_path")val poster: String,
    @ColumnInfo(name = "release_date")val releaseDate: String,
    @ColumnInfo(name = "overview") val overview: String
)

fun Movie.toDatabase() = MoviesEntity(movieId = movieId, title = title, poster = poster, releaseDate = releaseDate, overview = overview)