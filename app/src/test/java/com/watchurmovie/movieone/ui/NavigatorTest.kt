package com.watchurmovie.movieone.ui

import android.app.Activity
import android.content.Intent
import com.nhaarman.mockito_kotlin.anyOrNull
import com.watchurmovie.movieone.ui.home.Navigator
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.ref.WeakReference
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class NavigatorTest {
    @Mock
    lateinit var activity: Activity

    @Mock
    lateinit var intent: Intent


    private lateinit var navigator: Navigator

    @Before
    fun setUp() {
        navigator = Navigator(activity)
    }

    @Test
    fun `navigate shows image detail screen when route is image detail`() {
        navigator.navigate(Navigator.Route.MOVIE_DETAILS)
        Mockito.verify(activity).startActivity(anyOrNull())
    }
}