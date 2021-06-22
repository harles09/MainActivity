package com.expert.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.expert.core.domain.usecase.AppUseCase

class FavoriteTvShowViewModel(appUseCase: AppUseCase):ViewModel() {
    val favoriteTvShow = appUseCase.getFavoriteTvShow().asLiveData()
}