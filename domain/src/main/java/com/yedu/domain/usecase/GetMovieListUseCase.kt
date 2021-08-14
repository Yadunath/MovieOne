package com.yedu.domain.usecase

import com.yedu.domain.model.MovieItem
import com.yedu.domain.repository.MovieRepository
import javax.inject.Inject

import kotlin.Exception

class GetMovieListUseCase @Inject constructor(private val movieRepository: MovieRepository){
sealed class Result {
    object Loading : Result()
    data class Success(val movieList: List<MovieItem>?) : Result()
    data class Failure(val throwable: Throwable) : Result()
}

    suspend fun getTopRatedMovies():Result{
        return try {
            Result.Success(movieRepository.getTopRated().results)
        } catch (ex: Exception) {
            System.out.println("Repository "+ex.toString())
            Result.Failure(ex)
        }
    }


}