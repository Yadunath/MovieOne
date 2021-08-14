package com.watchurmovie.movieone

import android.app.Application
import android.util.Log
import com.yedu.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        inject()
    }
     fun inject(){

    }
}