package com.example.tmovies.data.models

import com.google.gson.annotations.SerializedName

data class MoviesPageModel(
    @SerializedName("results")
    val movieList: List<MovieModel>
)
