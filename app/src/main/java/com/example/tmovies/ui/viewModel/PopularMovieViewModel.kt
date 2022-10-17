package com.example.tmovies.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmovies.data.repositories.MostPopularMovieRepository
import com.example.tmovies.domain.GetPopularMoviesUseCase
import com.example.tmovies.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase): ViewModel() {
    val popularMovies = MutableLiveData<List<Movie>>()

    fun onCreate(page: Int) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.d("Network", "Caught $exception")
        }
        viewModelScope.launch(handler) {
            val result = getPopularMoviesUseCase(page)
            popularMovies.postValue(result)
        }
    }
}