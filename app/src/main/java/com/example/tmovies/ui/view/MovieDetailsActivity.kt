package com.example.tmovies.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.tmovies.databinding.ActivityMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    companion object {
        private const val URL = "https://image.tmdb.org/t/p/w500/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMovieInfo()
    }

    private fun getMovieInfo() {
        val intent = intent
        val poster = intent.getStringExtra("poster")
        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val releaseDate = intent.getStringExtra("release_date")

        binding.poster.load(URL + poster)
        binding.title.text = title
        binding.date.text = releaseDate
        binding.overview.text =  overview
    }
}