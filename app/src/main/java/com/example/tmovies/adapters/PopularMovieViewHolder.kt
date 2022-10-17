package com.example.tmovies.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tmovies.databinding.MoviePopularItemBinding
import com.example.tmovies.domain.model.Movie

class PopularMovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = MoviePopularItemBinding.bind(view)
    private companion object {
        const val URL = "https://image.tmdb.org/t/p/w500/"
    }

    fun bind(movieModel: Movie) {
        binding.poster.load(URL + movieModel.poster)
        binding.tvTitle.text = movieModel.title
        binding.tvOverview.text = movieModel.overview
    }
}