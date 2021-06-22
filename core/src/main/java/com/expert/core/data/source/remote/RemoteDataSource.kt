package com.expert.core.data.source.remote

import android.util.Log
import com.expert.core.BuildConfig
import com.expert.core.data.source.remote.network.ApiResponse
import com.expert.core.data.source.remote.network.ApiService
import com.expert.core.data.source.remote.response.MovieResponse
import com.expert.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


class RemoteDataSource(private val apiService: ApiService) {


    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {

        return flow{
            try{
                val response = apiService.getMovies(BuildConfig.apiKey)
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovie(movieId:String): Flow<ApiResponse<String>> {

        return flow{
            try{
                val response = apiService.getMovieDetails(movieId,BuildConfig.apiKey)
                val dataArray = response.originalTitle
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.originalTitle))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {

        return flow{
            try{
                val response = apiService.getTvShows(BuildConfig.apiKey)
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShow(tvShowId:String): Flow<ApiResponse<String>> {

        return flow{
            try{
                val response = apiService.getTvShowDetails(tvShowId,BuildConfig.apiKey)
                val dataArray = response.originalName
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.originalName))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}