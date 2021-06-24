package com.expert.core.utils

import com.expert.core.data.source.local.entity.MovieEntity
import com.expert.core.data.source.local.entity.TvShowEntity
import com.expert.core.data.source.remote.response.MovieResponse
import com.expert.core.data.source.remote.response.TvShowResponse
import com.expert.core.domain.model.Movie
import com.expert.core.domain.model.TvShow


object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                movieTitle = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterNow,
                releaseDate = it.releaseNow,
                voteAverage = it.voteAverage,
                favorited = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                movieTitle = it.movieTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                favorited = it.favorited
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        movieTitle = input.movieTitle,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        favorited = input.favorited
    )

    fun tvMapResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvshowList = ArrayList<TvShowEntity>()
        input.map {
            val tvshow = TvShowEntity(
                tvShowId = it.id,
                tvShowTitle = it.originalName,
                overview = it.overview,
                posterPath = it.posterPath,
                firstAirDate = it.firstAirDate,
                voteAverage = it.voteAverage,
                favorited = false
            )
            tvshowList.add(tvshow)
        }
        return tvshowList
    }

    fun tvMapEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                tvShowId = it.tvShowId,
                tvShowTitle = it.tvShowTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                firstAirDate = it.firstAirDate,
                voteAverage = it.voteAverage,
                favorited = it.favorited
            )
        }

    fun tvMapDomainToEntity(input: TvShow) = TvShowEntity(
        tvShowId = input.tvShowId,
        tvShowTitle = input.tvShowTitle,
        overview = input.overview,
        posterPath = input.posterPath,
        firstAirDate = input.firstAirDate,
        voteAverage = input.voteAverage,
        favorited = input.favorited
    )
}