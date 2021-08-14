package com.watchurmovie.movieone.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.idroidz.util.TestCoroutineRule
import com.watchurmovie.movieone.dagger.module.ApiModule.Companion.BASE_IMAGE_URL
import com.watchurmovie.movieone.ui.details.viewmodel.DetailsViewModel
import com.watchurmovie.movieone.ui.home.viewmodel.MainViewModel
import com.watchurmovie.movieone.util.Constants
import com.yedu.domain.model.MovieItem
import com.yedu.domain.model.ProviderListItem
import com.yedu.domain.usecase.GetMovieListUseCase
import com.yedu.domain.usecase.GetMovieProviderUseCase
import junit.framework.Assert
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
class DetailsViewModelTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var detailsViewModel: DetailsViewModel

    @Mock
    lateinit var getMovieProviderUseCase: GetMovieProviderUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailsViewModel = DetailsViewModel( getMovieProviderUseCase)
    }
    @Test
    fun test_fetchProviderList_returns_provider_list() {
        testCoroutineRule.runBlockingTest {
            val providerlist = mutableListOf<ProviderListItem>()
            providerlist.add(0, ProviderListItem("12","",""))
            val result = GetMovieProviderUseCase.Result.Success(providerlist)
            BDDMockito.given(getMovieProviderUseCase.getProviderList("12")).willReturn(result)
            detailsViewModel.getMovieDetails(MovieItem("12","","","","","",""))
            Assert.assertEquals(detailsViewModel.movieProviderImage.get(),BASE_IMAGE_URL+providerlist.get(0).logo_path)
        }
    }
    @Test
    fun `test fetchProviderList returns failure response`() {
        testCoroutineRule.runBlockingTest {
            val movieItem = MovieItem("2w","title","","","","","")
            val result=GetMovieProviderUseCase.Result.Failure(Exception())
            BDDMockito.given(getMovieProviderUseCase.getProviderList("")).willReturn(result)
            detailsViewModel.getMovieDetails(movieItem)
            org.junit.Assert.assertEquals(detailsViewModel.providerAvailability.get(), false)

        }
    }
}