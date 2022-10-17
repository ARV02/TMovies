package com.example.tmovies.domain

import com.example.tmovies.data.database.entities.toDatabase
import com.example.tmovies.data.repositories.MostPopularMovieRepository
import com.example.tmovies.domain.model.Movie
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val popularMoviesRepository: MostPopularMovieRepository){

    suspend operator fun invoke(page: Int): List<Movie> {
        val movies = popularMoviesRepository.getPopularMoviesFromApi(page)
        return if(movies.isNotEmpty()) {
            popularMoviesRepository.clearMovies()
            popularMoviesRepository.insertMovies(movies.map { it.toDatabase() })
            movies
        } else {
            popularMoviesRepository.getPopularMoviesFromDB()
        }
    }
}