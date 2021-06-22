package com.expert.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.expert.core.domain.usecase.AppUseCase

class FavoriteMovieViewModel(appUseCase: AppUseCase):ViewModel() {
    val favoriteMovie = appUseCase.getFavoriteMovie().asLiveData()
}