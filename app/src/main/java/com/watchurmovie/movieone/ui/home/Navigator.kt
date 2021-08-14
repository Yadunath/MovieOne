package com.watchurmovie.movieone.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.watchurmovie.movieone.ui.details.DetailsActivity
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.ref.WeakReference
import javax.inject.Inject

class Navigator  @Inject constructor(@ApplicationContext val context: Context) {
    enum class Route{
        MOVIE_DETAILS
    }
    fun navigate(route: Route, bundle: Bundle = Bundle()) {
        when (route) {
            Route.MOVIE_DETAILS -> { showNextScreen(DetailsActivity::class.java, bundle) }
        }
    }
    private fun showNextScreen(clazz: Class<*>, bundle: Bundle) {
        context.startActivity(Intent(context, clazz).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtras(bundle))
    }

}