package com.yedu.data.network

import com.yedu.domain.model.MovieResponse
import retrofit2.http.GET

interface RetrofitMoviesEndPoint{

    @GET("trending/all/day")
    suspend fun getMoviesList():MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies():MovieResponse
}