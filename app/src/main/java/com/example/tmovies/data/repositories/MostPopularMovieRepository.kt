package com.example.tmovies.data.repositories

import com.example.tmovies.data.database.dao.MoviesDao
import com.example.tmovies.data.database.entities.MoviesEntity
import com.example.tmovies.data.network.PopularMoviesService
import com.example.tmovies.domain.model.Movie
import com.example.tmovies.domain.model.toDomain
import javax.inject.Inject

class MostPopularMovieRepository @Inject constructor(private val popularMoviesService: PopularMoviesService,
                                                     private val moviesDao: MoviesDao) {

    suspend fun getPopularMoviesFromApi(page: Int):List<Movie> {
        val response = popularMoviesService.mostPopularMovie(page)
        return response.movieList.map { it.toDomain() }
    }

    suspend fun getPopularMoviesFromDB():List<Movie> {
        val response = moviesDao.gelAllMovies()
        return response.map { it.toDomain() }
    }

    suspend fun insertMovies(movies: List<MoviesEntity>) {
        moviesDao.insertAll(movies)
    }

    suspend fun clearMovies() {
        moviesDao.deleteAllMovies()
    }
}