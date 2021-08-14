package com.watchurmovie.movieone.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.watchurmovie.movieone.R
import com.watchurmovie.movieone.databinding.ActivityMainBinding
import com.watchurmovie.movieone.ui.base.BaseActivity
import com.watchurmovie.movieone.ui.home.adapter.MovieListViewAdapter
import com.watchurmovie.movieone.ui.home.viewmodel.MainViewModel
import com.yedu.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding =DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewmodel = mainViewModel

        mainViewModel.fetchMovies()
    }
    companion object{
        @JvmStatic
        @BindingAdapter("adapter")
        fun bindRecyclerView(recyclerView: RecyclerView,viewModel: MainViewModel){
            Log.d("RepositoryTAG","viewModel ")
            var adapter = MovieListViewAdapter(viewModel.movieList)
            adapter.onItemClickListener = { viewModel.onMovieItemClicked(it) }

            recyclerView.layoutManager  =LinearLayoutManager(recyclerView.context)
            recyclerView.adapter =adapter

        }
    }

}