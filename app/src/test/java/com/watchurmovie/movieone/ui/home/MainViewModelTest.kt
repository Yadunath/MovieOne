package com.watchurmovie.movieone.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.idroidz.util.TestCoroutineRule
import com.watchurmovie.movieone.ui.home.Navigator
import com.watchurmovie.movieone.ui.home.viewmodel.MainViewModel
import com.yedu.domain.model.MovieItem
import com.yedu.domain.usecase.GetMovieListUseCase
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

@RunWith(JUnit4::class)
class MainViewModelTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var getMovieListUseCase: GetMovieListUseCase

    @Mock
    lateinit var navigator: Navigator
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel( getMovieListUseCase, navigator)
    }

    @Test
    fun test_fetchMovies_returns_movies_list() {
        testCoroutineRule.runBlockingTest {
            val moviesList = mutableListOf<MovieItem>()
            val result = GetMovieListUseCase.Result.Success(moviesList)
            BDDMockito.given(getMovieListUseCase.getTopRatedMovies()).willReturn(result)
            mainViewModel.fetchMovies()
            assertEquals(mainViewModel.movieList, moviesList)
        }
    }
    @Test
    fun test_fetchNews_returns_error() {
        testCoroutineRule.runBlockingTest {
            val result = GetMovieListUseCase.Result.Failure(Exception())
            BDDMockito.given(getMovieListUseCase.getTopRatedMovies()).willReturn(result)

            mainViewModel.fetchMovies()
            Assert.assertEquals(mainViewModel.networkFailure.get(), true)

        }

    }

}