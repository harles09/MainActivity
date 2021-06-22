package com.expert.mainactivity.detail

import androidx.lifecycle.ViewModel
import com.expert.core.domain.model.Movie
import com.expert.core.domain.model.TvShow
import com.expert.core.domain.usecase.AppUseCase

class DetailViewModel(private val appUseCase: AppUseCase):ViewModel() {
    fun setFavoriteMovie(movie:Movie,newStatus:Boolean) = appUseCase.setFavoriteMovie(movie,newStatus)

    fun setFavoriteTvShow(tvShow: TvShow,newStatus: Boolean) = appUseCase.setFavoriteTvShow(tvShow,newStatus)
}