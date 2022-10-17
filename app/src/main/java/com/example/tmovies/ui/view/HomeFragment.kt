package com.example.tmovies.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmovies.adapters.PopularMoviesAdapter
import com.example.tmovies.databinding.FragmentHomeBinding
import com.example.tmovies.domain.model.Movie
import com.example.tmovies.ui.viewModel.PopularMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: PopularMoviesAdapter

    private val popularMovieViewModel: PopularMovieViewModel by viewModels()

    private var moviesList = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PopularMoviesAdapter(moviesList, requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        }
        initViewModel()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        popularMovieViewModel.popularMovies.observe(viewLifecycleOwner) { list ->
            moviesList.addAll(list)
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
        popularMovieViewModel.onCreate(1);
    }
}