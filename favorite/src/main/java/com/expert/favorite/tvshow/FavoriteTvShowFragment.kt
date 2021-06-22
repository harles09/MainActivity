package com.expert.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.core.ui.TvShowAdapter
import com.expert.favorite.databinding.FragmentFavoriteTvShowBinding
import com.expert.mainactivity.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteTvShowFragment : Fragment() {

    private var _binding : FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding!!
    private val tvShowFavoriteViewModel: FavoriteTvShowViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                intent.putExtra(DetailActivity.EXTRA_TYPE, "Movie")
                startActivity(intent)
            }

            tvShowFavoriteViewModel.favoriteTvShow.observe(viewLifecycleOwner,{ dataTvShow ->
                tvShowAdapter.setData(dataTvShow)
                binding.viewEmpty.root.visibility = if(dataTvShow.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvFavoriteTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }





}