package com.example.tmovies.data.network

import com.example.tmovies.data.models.MoviesPageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularMoviesService @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun mostPopularMovie(page: Int):MoviesPageModel {
        return withContext(Dispatchers.IO) {
            apiInterface.getPopularMovies(page)
        }
    }
}