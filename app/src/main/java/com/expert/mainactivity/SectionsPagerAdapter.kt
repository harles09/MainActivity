package com.expert.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.expert.mainactivity.movie.MovieFragment
import com.expert.mainactivity.tvshow.TvShowFragment

class SectionsPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    private var fragment: Fragment? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        when (position) {
            0 -> {
                fragment = MovieFragment()
            }
            1 -> {
                fragment = TvShowFragment()
            }
        }

        fragment?.arguments = bundle
        return fragment as Fragment
    }
}