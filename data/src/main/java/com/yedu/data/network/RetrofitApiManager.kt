package com.yedu.data.network

import com.yedu.domain.model.MovieResponse
import com.yedu.domain.model.ProviderResult
import javax.inject.Inject

class RetrofitApiManager @Inject constructor(private val moviesEndPoint: RetrofitMoviesEndPoint,private val providerEndPoint: RetrofitMovieProviderEndPoint):
    ApiManager {
    override suspend fun getMovieList(): MovieResponse {
        return moviesEndPoint.getMoviesList()
    }

    override suspend fun getTopRated(): MovieResponse {
        return moviesEndPoint.getTopRatedMovies()
    }

    override suspend fun getMovieProviderList(movieId:String): ProviderResult {
        return providerEndPoint.getProviderNames(movieId)
    }


}