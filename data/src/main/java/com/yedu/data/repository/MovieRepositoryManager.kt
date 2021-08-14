package com.yedu.data.repository


import com.yedu.data.network.ApiManager
import com.yedu.domain.model.MovieResponse
import com.yedu.domain.model.ProviderResult
import com.yedu.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class MovieRepositoryManager @Inject constructor(private val apiManager: ApiManager) :MovieRepository {

    override suspend fun getTopRated(): MovieResponse {
        return apiManager.getTopRated()
    }

    override suspend fun getProviderList(movieId:String): ProviderResult {
        return apiManager.getMovieProviderList(movieId)
    }


}