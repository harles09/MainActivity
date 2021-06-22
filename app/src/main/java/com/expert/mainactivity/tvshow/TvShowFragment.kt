package com.expert.mainactivity.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.core.data.Resource
import com.expert.core.ui.TvShowAdapter
import com.expert.mainactivity.R
import com.expert.mainactivity.databinding.FragmentTvShowBinding
import com.expert.mainactivity.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class TvShowFragment : Fragment() {

    private val tvShowViewModel: TvShowViewModel by viewModel()
    private var _binding:FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                intent.putExtra(DetailActivity.EXTRA_TYPE, "TvShow")
                startActivity(intent)
            }


            tvShowViewModel.getTvShow.observe(viewLifecycleOwner) { tvshow ->
                if (tvshow != null) {
                    when (tvshow) {
                        is Resource.Loading -> binding.progressBar.visibility =
                            View.VISIBLE
                        is Resource.Success-> {
                            binding.progressBar.visibility = View.GONE
                            tvShowAdapter.setData(tvshow.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                tvshow.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }
            with(binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }


}