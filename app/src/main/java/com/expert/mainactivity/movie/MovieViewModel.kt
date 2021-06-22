package com.expert.mainactivity.movie
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.expert.core.domain.usecase.AppUseCase

class MovieViewModel(appUseCase: AppUseCase):ViewModel() {
    val getMovie = appUseCase.getAllMovie().asLiveData()
}