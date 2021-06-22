package com.expert.core.data

import com.expert.core.data.source.local.LocalDataSource
import com.expert.core.data.source.remote.RemoteDataSource
import com.expert.core.data.source.remote.network.ApiResponse
import com.expert.core.data.source.remote.response.MovieResponse
import com.expert.core.data.source.remote.response.TvShowResponse
import com.expert.core.domain.model.Movie
import com.expert.core.domain.model.TvShow
import com.expert.core.domain.repository.IAppRepository
import com.expert.core.utils.AppExecutors
import com.expert.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IAppRepository {

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): AppRepository =
            instance ?: synchronized(this) {
               instance
                    ?: AppRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()


    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun getMovieTitle(name:String): Flow<List<Movie>> {
        return localDataSource.getMovieTitle(name).map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }

    override fun getAllTvShows(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getTvShows().map { DataMapper.tvMapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.tvMapResponsesToEntities(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()



    override fun getFavoriteTvShow(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvShow().map { DataMapper.tvMapEntitiesToDomain(it) }
    }

    override fun getTvShowTitle(name:String): Flow<List<TvShow>> {
        return localDataSource.getTvShowTitle(name).map { DataMapper.tvMapEntitiesToDomain(it) }
    }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) {
        val tvshowEntity = DataMapper.tvMapDomainToEntity(tvShow)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvshowEntity, state)
        }
    }

}