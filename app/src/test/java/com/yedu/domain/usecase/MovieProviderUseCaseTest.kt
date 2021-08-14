package com.yedu.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.idroidz.util.TestCoroutineRule
import com.yedu.domain.model.*
import com.yedu.domain.repository.MovieRepository
import junit.framework.Assert
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
import java.lang.NullPointerException

@RunWith(JUnit4::class)
class MovieProviderUseCaseTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var movieRepository: MovieRepository
    private lateinit var getMovieProviderUseCase: GetMovieProviderUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getMovieProviderUseCase = GetMovieProviderUseCase(movieRepository)
    }
    @Test
    fun `get providers list with success response`() {
        testCoroutineRule.runBlockingTest {
            val networkMovieList = mutableListOf<MovieItem>()
            networkMovieList.add(MovieItem("","","","","","",""))
            val movieList = mutableListOf<MovieItem>()
            movieList.add(MovieItem("","","","","","", ""))
            val expectedResult = GetMovieListUseCase.Result.Success(movieList)
            val movieResponse = MovieResponse("0", movieList)
            BDDMockito.given(movieRepository.getTopRated()).willReturn(movieResponse)
            val actualResult = getMovieProviderUseCase.getProviderList("")
            Assert.assertEquals(expectedResult, actualResult)
        }
    }
    @Test
    fun `get movie provider with success response`() {
        testCoroutineRule.runBlockingTest {
            val providerlist = mutableListOf<ProviderListItem>()
            providerlist.add(0, ProviderListItem("12","",""))

            val providerItem =ProviderItem("",providerlist)
            val providerResultItem = ProviderResultItem(providerItem)
            val providerResult = ProviderResult("",providerResultItem)
            val expectedResult = GetMovieProviderUseCase.Result.Success(providerlist)

            BDDMockito.given(movieRepository.getProviderList("")).willReturn(providerResult)
            val actualResult = getMovieProviderUseCase.getProviderList("")
            assertEquals(expectedResult, actualResult)
        }
    }

}