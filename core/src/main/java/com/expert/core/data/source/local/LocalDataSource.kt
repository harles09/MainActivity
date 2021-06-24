package com.expert.core.data.source.local

import com.expert.core.data.source.local.entity.MovieEntity
import com.expert.core.data.source.local.entity.TvShowEntity
import com.expert.core.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val appDao: AppDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(appDao)
            }
    }

    fun getMovies(): Flow<List<MovieEntity>> = appDao.getMovies()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = appDao.getFavoritedMovie()

    fun getMovieTitle(name:String): Flow<List<MovieEntity>> = appDao.getMovieTitle(name)

    suspend fun insertMovie(movieList: List<MovieEntity>) = appDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        appDao.updateFavoriteMovie(movie)
    }

    fun getTvShows(): Flow<List<TvShowEntity>> = appDao.getTvShows()

    fun getFavoriteTvShow(): Flow<List<TvShowEntity>> = appDao.getFavoritedTvShow()

    fun getTvShowTitle(name:String): Flow<List<TvShowEntity>> = appDao.getTvShowTitle(name)

    suspend fun insertTvShow(tvshowList: List<TvShowEntity>) = appDao.insertTvShow(tvshowList)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        appDao.updateFavoriteTvShow(tvShow)
    }
}