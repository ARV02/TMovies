package com.example.tmovies.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmovies.R
import com.example.tmovies.domain.model.Movie
import com.example.tmovies.ui.view.MovieDetailsActivity

class PopularMoviesAdapter(private val popularMovieList: List<Movie>,
                           private val context: Context):RecyclerView.Adapter<PopularMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PopularMovieViewHolder(layoutInflater.inflate(R.layout.movie_popular_item, parent, false))
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val items = popularMovieList[position]
        holder.bind(items)
        
        holder.itemView.setOnClickListener { 
            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            intent.apply {
                putExtra("poster", items.poster)
                putExtra("title", items.title)
                putExtra("overview", items.overview)
                putExtra("release_date", items.releaseDate)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = popularMovieList.size
}