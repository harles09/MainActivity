package com.expert.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.expert.favorite.movie.FavoriteMovieFragment
import com.expert.favorite.tvshow.FavoriteTvShowFragment

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    private var fragment: Fragment? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        when (position) {
            0 -> {
                fragment = FavoriteMovieFragment()
            }
            1 -> {
                fragment = FavoriteTvShowFragment()
            }
        }

        fragment?.arguments = bundle
        return fragment as Fragment
    }
}