package com.expert.mainactivity.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.expert.core.domain.model.Movie
import com.expert.core.domain.model.TvShow
import com.expert.mainactivity.R
import com.expert.mainactivity.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModel()
    private var menu: Menu? = null
    var favorite:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar3.visibility = View.VISIBLE
        val type = intent.getStringExtra(EXTRA_TYPE)
            if (type.equals("Movie", ignoreCase = true)) {
                val data = intent.getParcelableExtra<Movie>(EXTRA_DATA)
                getMovie(data)
            }else if (type.equals("TvShow", ignoreCase = true)){
                val data = intent.getParcelableExtra<TvShow>(EXTRA_DATA)
                getTvShow(data)
            }

        binding.floatingActionButton.setOnClickListener {
            val title = binding.tvTitle.text
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Share $title Movies/TvShow")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to: "))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        val type = intent.getStringExtra(EXTRA_TYPE)
        if(type.equals("Movie",ignoreCase = true)){
            val data = intent.getParcelableExtra<Movie>(EXTRA_DATA)
            data?.let {
                setFavoriteState(it.favorited)
            }
        }else if (type.equals("TvShow",ignoreCase = true)){
            val data = intent.getParcelableExtra<TvShow>(EXTRA_DATA)
            data?.let {
                setFavoriteState(it.favorited)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.bt_favorite) {
            val type = intent.getStringExtra(EXTRA_TYPE)
            if (type.equals("Movie", ignoreCase = true)) {
                val data = intent.getParcelableExtra<Movie>(EXTRA_DATA)
                data?.let {
                    favorite = !favorite
                    detailViewModel.setFavoriteMovie(data,favorite)
                }
            }else if(type.equals("TvShow", ignoreCase = true)){
                val data = intent.getParcelableExtra<TvShow>(EXTRA_DATA)
                data?.let {
                    favorite = !favorite
                    detailViewModel.setFavoriteTvShow(data,favorite)
                }
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.bt_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun getMovie(data:Movie?){
        data?.let {
            binding.progressBar3.visibility = View.GONE
            supportActionBar?.title = data.movieTitle
            binding.tvTitle.text = data.movieTitle
            binding.rvReleasedate.text = data.releaseDate
            binding.tvRating.text = data.voteAverage
            binding.tvDesc.text = data.overview
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500${data.posterPath}")
                .into(binding.imgPoster)
            favorite = data.favorited
        }
    }

    private fun getTvShow(data:TvShow?){
        data?.let {
            binding.progressBar3.visibility = View.GONE
            supportActionBar?.title = data.tvShowTitle
            binding.tvTitle.text = data.tvShowTitle
            binding.rvReleasedate.text = data.firstAirDate
            binding.tvRating.text = data.voteAverage
            binding.tvDesc.text = data.overview
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500${data.posterPath}")
                .into(binding.imgPoster)
            favorite = data.favorited
        }
    }
}


