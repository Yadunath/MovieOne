package com.watchurmovie.movieone.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.watchurmovie.movieone.R
import com.watchurmovie.movieone.dagger.module.ApiModule.Companion.BASE_IMAGE_URL
import com.watchurmovie.movieone.databinding.MovieItemBinding
import com.watchurmovie.movieone.util.Constants
import com.yedu.domain.model.MovieItem

class MovieListViewAdapter(val movieList: ObservableList<MovieItem>) : ObservableRecyclerViewAdapter<MovieItem,MovieListViewAdapter.MovieListViewHolder>(movieList){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),
            onItemClickListener
        )
    }
    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
       holder.bind(holder.itemView.context,getItem(position))
    }

    class  MovieListViewHolder(private val binding:MovieItemBinding,
                               private val onItemClickListener: ((item: Any) -> Unit)?
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, movieItem: MovieItem) {
            Glide.with(context).load(BASE_IMAGE_URL+movieItem.poster_path)
                .centerCrop().error(R.drawable.ic_launcher_background).into(binding.imageView)
            binding.movieTitle.text = movieItem.title
            binding.movieYear.text = movieItem.release_date.substring(0,4)
            binding.root.setOnClickListener { onItemClickListener?.invoke(movieItem) }
        }
    }
}