package com.watchurmovie.movieone.ui.home.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.watchurmovie.movieone.ui.base.BaseViewModel
import com.watchurmovie.movieone.ui.details.DetailsActivity
import com.watchurmovie.movieone.ui.home.Navigator
import com.yedu.domain.model.MovieItem
import com.yedu.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieListUseCase: GetMovieListUseCase,
                                        private val navigator: Navigator,
) : BaseViewModel(){
    val movieList =ObservableArrayList<MovieItem>()
    val networkFailure = ObservableBoolean(false)

    fun fetchMovies(){
        viewModelScope.launch {
            coroutineScope {
                val topRatedList = movieListUseCase.getTopRatedMovies()
                when(val result = topRatedList){
                    is GetMovieListUseCase.Result.Success ->{
                        movieList.addAll(result.movieList!!)
                        networkFailure.set(false)
                    }
                    is GetMovieListUseCase.Result.Failure -> {
                        networkFailure.set(true)
                    }
                }
            }
        }
    }
    fun onMovieItemClicked(movieItem: Any) {
        navigator.navigate(Navigator.Route.MOVIE_DETAILS, Bundle().apply {
            putSerializable(DetailsActivity.EXTRA_MOVIE_DETAILS, (movieItem as MovieItem))
        })
    }
}