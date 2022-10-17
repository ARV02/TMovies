package com.example.tmovies.domain.model

import com.example.tmovies.data.database.entities.MoviesEntity
import com.example.tmovies.data.models.MovieModel

data class Movie(
    val movieId: Int,
    val title: String,
    val poster: String,
    val releaseDate: String,
    val overview: String
)

fun MovieModel.toDomain() = Movie(movieId, title, poster, releaseDate, overview)
fun MoviesEntity.toDomain() = Movie(movieId, title, poster, releaseDate, overview)
