package com.watchurmovie.movieone.ui.details.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.watchurmovie.movieone.dagger.module.ApiModule.Companion.BASE_IMAGE_URL
import com.watchurmovie.movieone.ui.base.BaseViewModel
import com.yedu.domain.model.MovieItem
import com.yedu.domain.model.ProviderListItem
import com.yedu.domain.usecase.GetMovieProviderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getMovieProviderUseCase: GetMovieProviderUseCase):BaseViewModel() {

    val movieTitle =ObservableField<String>()
    val movieOverView = ObservableField<String>()
    val movieBanner = ObservableField<String>()
    val movieProviderImage = ObservableField<String>()
    val providerAvailability = ObservableBoolean(false)
    fun getMovieDetails(movieItem: MovieItem){
        viewModelScope.launch {
            coroutineScope {
                 movieItem.let {
                   val providerList = getMovieProviderUseCase.getProviderList(movieItem.id)
                    when(val result = providerList){
                        is GetMovieProviderUseCase.Result.Success ->{
                            result.providerList?.let {
                                movieProviderImage.set(BASE_IMAGE_URL+result.providerList!!.get(0).logo_path)
                                providerAvailability.set(true)
                            }
                            setDetailsData(movieItem)
                        }
                        is GetMovieProviderUseCase.Result.Failure ->{
                                setDetailsData(movieItem)
                        }
                    }
                }
            }
        }
    }
    private fun setDetailsData(movieItem: MovieItem){
        movieTitle.set(movieItem.title)
        movieOverView.set(movieItem.overview)
        movieBanner.set(BASE_IMAGE_URL+movieItem.backdrop_path)

    }
}