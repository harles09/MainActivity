package com.expert.favorite

import com.expert.favorite.movie.FavoriteMovieViewModel
import com.expert.favorite.tvshow.FavoriteTvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteTvShowViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
}