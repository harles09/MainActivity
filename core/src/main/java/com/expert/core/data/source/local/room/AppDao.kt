package com.expert.core.data.source.local.room

import androidx.room.*
import com.expert.core.data.source.local.entity.MovieEntity
import com.expert.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where favorited = 1")
    fun getFavoritedMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where movieTitle LIKE :name")
    fun getMovieTitle(name:String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM tvshow")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshow where favorited = 1")
    fun getFavoritedTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshow where tvShowTitle LIKE :name")
    fun getTvShowTitle(name:String): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvshow: List<TvShowEntity>)

    @Update
    fun updateFavoriteTvShow(tvshow: TvShowEntity)
}
