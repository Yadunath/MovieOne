package com.watchurmovie.movieone.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.watchurmovie.movieone.R
import com.watchurmovie.movieone.dagger.module.ApiModule.Companion.BASE_IMAGE_URL
import com.watchurmovie.movieone.databinding.ActivityDetailsBinding
import com.watchurmovie.movieone.ui.base.BaseActivity
import com.watchurmovie.movieone.ui.details.viewmodel.DetailsViewModel
import com.watchurmovie.movieone.ui.home.viewmodel.MainViewModel
import com.yedu.domain.model.MovieItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {
    lateinit var detailsViewModel: DetailsViewModel
    companion object {
        const val EXTRA_MOVIE_DETAILS = "movie_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding :ActivityDetailsBinding=DataBindingUtil.setContentView(this,R.layout.activity_details)
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val movieItem = intent.getSerializableExtra(EXTRA_MOVIE_DETAILS)as MovieItem

        binding.detailsViewModel = detailsViewModel
        detailsViewModel.getMovieDetails(movieItem)
    }
}