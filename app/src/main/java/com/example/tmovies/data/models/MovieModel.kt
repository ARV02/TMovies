package com.example.tmovies.data.models

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id")
    val movieId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("overview")
    val overview: String
)
