package com.yedu.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.idroidz.util.TestCoroutineRule
import com.yedu.domain.model.MovieItem
import com.yedu.domain.model.MovieResponse
import com.yedu.domain.repository.MovieRepository
import junit.framework.Assert.assertEquals
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
class MovieListUseCaseTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var movieRepository: MovieRepository
    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getMovieListUseCase = GetMovieListUseCase(movieRepository)
    }
    @Test
    fun `get movie list with success response`() {
        testCoroutineRule.runBlockingTest {
            val networkMovieList = mutableListOf<MovieItem>()
            networkMovieList.add(MovieItem("","","","","","",""))
            val movieList = mutableListOf<MovieItem>()
            movieList.add(MovieItem("","","","","","", ""))
            val expectedResult = GetMovieListUseCase.Result.Success(movieList)
            val movieResponse = MovieResponse("0", movieList)
            BDDMockito.given(movieRepository.getTopRated()).willReturn(movieResponse)
            val actualResult = getMovieListUseCase.getTopRatedMovies()
            assertEquals(expectedResult, actualResult)
        }
    }

}