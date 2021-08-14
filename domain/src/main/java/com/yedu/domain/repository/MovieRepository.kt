package com.yedu.domain.repository

import com.yedu.domain.model.MovieResponse
import com.yedu.domain.model.ProviderResult


interface MovieRepository {


    suspend fun getTopRated(): MovieResponse

    suspend fun getProviderList(movieId:String):ProviderResult


}