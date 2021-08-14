package com.watchurmovie.movieone.dagger.module

import android.content.Context
import android.util.Log
import com.watchurmovie.movieone.BuildConfig
import com.watchurmovie.movieone.ui.home.Navigator
import com.yedu.data.network.ApiManager
import com.yedu.data.network.RetrofitApiManager
import com.yedu.data.network.RetrofitMovieProviderEndPoint
import com.yedu.data.network.RetrofitMoviesEndPoint
import com.yedu.data.repository.MovieRepositoryManager
import com.yedu.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    companion object {
        private const val BASE_URL:String ="https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL:String ="https://image.tmdb.org/t/p/w500"
        private const val  API_KEY ="api_key"
        private const val API_KEY_VALUE ="57ec817a04be27f196d3da087d6b1a28"
    }
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(
        Interceptor {
            val original = it.request()
            val originalhttpUrl = original.url()
            val url = originalhttpUrl.newBuilder().addQueryParameter(API_KEY, API_KEY_VALUE).build()
            val requestBuilder = original.newBuilder().url(url)
            val request = requestBuilder.build()
            return@Interceptor it.proceed(request)
        }
    ).build()

    @Provides
    @Singleton
    fun provideApiManager(apiManager: RetrofitApiManager): ApiManager {
        return apiManager
    }

    @Provides
    @Singleton
    fun provideRetrofitMoviesEndPoint(): RetrofitMoviesEndPoint {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RetrofitMoviesEndPoint::class.java)
    }
    @Provides
    @Singleton
    fun providesRetrofitMovieProviderEndPoint() :RetrofitMovieProviderEndPoint{
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        return retrofit.create(RetrofitMovieProviderEndPoint::class.java)
    }
    @Provides
    @Singleton
    fun provideMovieRepository(apiManager: ApiManager): MovieRepository {
        return MovieRepositoryManager(apiManager)
    }



}