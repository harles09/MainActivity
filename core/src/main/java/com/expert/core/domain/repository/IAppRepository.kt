package com.expert.core.domain.repository

import com.expert.core.data.Resource
import com.expert.core.domain.model.Movie
import com.expert.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IAppRepository {

    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun getMovieTitle(name:String): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun getAllTvShows(): Flow<Resource<List<TvShow>>>

    fun getFavoriteTvShow(): Flow<List<TvShow>>

    fun getTvShowTitle(name:String): Flow<List<TvShow>>

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}