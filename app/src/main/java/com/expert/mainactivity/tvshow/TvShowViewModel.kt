package com.expert.mainactivity.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.expert.core.domain.usecase.AppUseCase

class TvShowViewModel(appUseCase: AppUseCase):ViewModel() {
    val getTvShow = appUseCase.getAllTvShow().asLiveData()
}