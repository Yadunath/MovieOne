package com.yedu.domain.usecase

import android.util.Log
import com.yedu.domain.model.MovieItem
import com.yedu.domain.model.ProviderListItem
import com.yedu.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieProviderUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val providerList: List<ProviderListItem>?) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    suspend fun getProviderList(movieId:String):Result{
        return try {
            Result.Success(movieRepository.getProviderList(movieId).results?.IN?.flatrate)
        } catch (ex: Exception) {
            System.out.println("Repository "+ex.toString())
            Result.Failure(ex)
        }
    }


}