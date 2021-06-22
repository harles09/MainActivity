package com.expert.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.core.ui.MovieAdapter
import com.expert.favorite.databinding.FragmentFavoriteMovieBinding
import com.expert.mainactivity.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteMovieFragment : Fragment() {
    private val movieFavoriteViewModel: FavoriteMovieViewModel by viewModel()
    private var _binding : FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentFavoriteMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                intent.putExtra(DetailActivity.EXTRA_TYPE, "Movie")
                startActivity(intent)
            }

            movieFavoriteViewModel.favoriteMovie.observe(viewLifecycleOwner,{ dataMovie ->
                movieAdapter.setData(dataMovie)
               binding.viewEmpty.root.visibility = if(dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvFavoriteMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }



}