package com.expert.core.domain.usecase

import com.expert.core.domain.model.Movie
import com.expert.core.domain.model.TvShow
import com.expert.core.domain.repository.IAppRepository


class AppInteractor(private val appRepository: IAppRepository):AppUseCase {
    override fun getAllMovie() = appRepository.getAllMovies()

    override fun getFavoriteMovie() = appRepository.getFavoriteMovie()

    override fun getMovieTitle(name:String) = appRepository.getMovieTitle(name)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = appRepository.setFavoriteMovie(movie, state)

    override fun getAllTvShow() = appRepository.getAllTvShows()

    override fun getFavoriteTvShow() = appRepository.getFavoriteTvShow()

    override fun getTvShowTitle(name:String) = appRepository.getTvShowTitle(name)

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) = appRepository.setFavoriteTvShow(tvShow, state)
}