package com.expert.core.data.source.remote.network

import com.expert.core.data.source.remote.response.ListMovieResponse
import com.expert.core.data.source.remote.response.ListTvShowResponse
import com.expert.core.data.source.remote.response.MovieResponse
import com.expert.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String): ListMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String?,
                        @Query("api_key") apiKey: String
    ) : MovieResponse

    @GET("tv/popular")
    suspend fun getTvShows(@Query("api_key") apiKey: String) : ListTvShowResponse


    @GET("tv/{tv_id}")
    suspend fun getTvShowDetails(@Path("tv_id") tvId: String,
                         @Query("api_key") apiKey: String
    ) : TvShowResponse
}