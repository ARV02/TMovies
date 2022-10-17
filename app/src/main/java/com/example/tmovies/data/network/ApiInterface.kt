package com.example.tmovies.data.network

import com.example.tmovies.data.models.MoviesPageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page")page: Int,
        @Query("include_adult")adult:Boolean = false,
        @Query("language")language: String = "en-US"
    ): MoviesPageModel
}