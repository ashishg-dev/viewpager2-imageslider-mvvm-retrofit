package com.devashish.trendingmovies.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devashish.trendingmovies.R
import com.devashish.trendingmovies.adapter.CastAdapter
import com.devashish.trendingmovies.databinding.ActivityMovieDetailsBinding
import com.devashish.trendingmovies.model.CastModel
import com.devashish.trendingmovies.model.TrendingMoviesModel
import com.devashish.trendingmovies.utilities.HttpStatus
import com.devashish.trendingmovies.viewmodel.TrendingMoviesViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    private var movieDetails: TrendingMoviesModel? = null
    private lateinit var activityMovieDetailsBinding: ActivityMovieDetailsBinding
    private val trendingMoviesViewModel by lazy {
        ViewModelProvider(this).get(TrendingMoviesViewModel::class.java)
    }
    private var listOfCast = ArrayList<CastModel>()
    private var castAdapter: CastAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMovieDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        activityMovieDetailsBinding.listener = this

        readIntent()
        initCastAdapter()
        getVideoCasts()

    }

    private fun initCastAdapter() {
        castAdapter = CastAdapter(listOfCast)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerViewCast.layoutManager = layoutManager
        recyclerViewCast.adapter = castAdapter
    }

    private fun readIntent() {
        movieDetails = intent.getParcelableExtra("movieDetails")
        activityMovieDetailsBinding.model = movieDetails
    }

    // close the current activity and redirect to previous activity
    fun onCloseClicked() {
        finish()
    }

    // getting cast of movie/tv show based on ID
    private fun getVideoCasts() {
        trendingMoviesViewModel.getVideoDetails(
            movieDetails!!.id, movieDetails!!.mediaType
        ).observe(this, Observer {
            when (it.status) {
                HttpStatus.LOADING -> {
                    // show progress bar
                    contentLoadingProgressBar.show()
                    recyclerViewCast.visibility = View.GONE
                }

                HttpStatus.SUCCESS -> {
                    // hide progress bar and display casts
                    listOfCast.clear()
                    it.data?.let { casts ->
                        listOfCast.addAll(casts)
                        castAdapter!!.notifyDataSetChanged()

                    }
                    contentLoadingProgressBar.hide()
                    recyclerViewCast.visibility = View.VISIBLE

                }

                HttpStatus.ERROR -> {
                    // hide progress bar
                    Toast.makeText(
                        this,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                    contentLoadingProgressBar.hide()
                    recyclerViewCast.visibility = View.GONE
                }
            }
        })
    }
}