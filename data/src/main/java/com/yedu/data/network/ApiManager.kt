package com.yedu.data.network

import com.yedu.domain.model.MovieResponse
import com.yedu.domain.model.ProviderResult

interface ApiManager {
    suspend fun getMovieList(): MovieResponse
    suspend fun getTopRated(): MovieResponse
    suspend fun getMovieProviderList(movieId:String): ProviderResult
}