package com.expert.mainactivity.di

import com.expert.core.domain.usecase.AppInteractor
import com.expert.core.domain.usecase.AppUseCase
import com.expert.mainactivity.detail.DetailViewModel
import com.expert.mainactivity.movie.MovieViewModel
import com.expert.mainactivity.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AppUseCase> { AppInteractor(get()) }
}

val viewModelModule = module {
    viewModel { TvShowViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}