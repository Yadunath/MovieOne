package com.yedu.data.network

import com.yedu.domain.model.ProviderResult
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMovieProviderEndPoint {
    @GET("movie/{movie_id}/watch/providers")
    suspend fun getProviderNames(@Path("movie_id") movieId: String): ProviderResult
}