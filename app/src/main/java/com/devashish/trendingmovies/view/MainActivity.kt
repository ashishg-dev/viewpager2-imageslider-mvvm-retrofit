package com.devashish.trendingmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.devashish.trendingmovies.R
import com.devashish.trendingmovies.adapter.SliderAdapter
import com.devashish.trendingmovies.databinding.ActivityMainBinding
import com.devashish.trendingmovies.model.TrendingMoviesModel
import com.devashish.trendingmovies.utilities.HttpStatus
import com.devashish.trendingmovies.viewmodel.TrendingMoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SliderAdapter.OnItemClickListener {

    private val trendingMoviesViewModel by lazy {
        ViewModelProvider(this).get(TrendingMoviesViewModel::class.java)
    }
    private lateinit var activityHomeBinding: ActivityMainBinding
    private val listTrendingMovies = ArrayList<TrendingMoviesModel>()
    private var sliderAdapter: SliderAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityHomeBinding.listener = this
        sliderAdapter = SliderAdapter(listTrendingMovies, this)
        handleEvent()
        observeTrendingMovies()
        setViewPagerProps()

    }

    // init viewpager props
    private fun setViewPagerProps() {
        viewPager.apply {
            adapter = sliderAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun handleEvent() {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val right = 1 - Math.abs(position)
            page.scaleY = 0.85f + right * 0.15f
        }
        viewPager.setPageTransformer(compositePageTransformer)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                activityHomeBinding.model = listTrendingMovies[position]
            }

        })
    }

    // when more details button clicked, redirect to next activity
    fun onVideoClick(model: TrendingMoviesModel) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movieDetails", model)
        startActivity(intent)
    }

    // when image clicked
    override fun onItemClicked(model: TrendingMoviesModel) {
        onVideoClick(model)
    }

    // get movies from api
    private fun observeTrendingMovies() {

        trendingMoviesViewModel.getTrendingVideos().observe(this, Observer {

            when (it.status) {
                HttpStatus.LOADING -> {
                    linearLayoutDetails.visibility = View.GONE
                    contentLoadingProgressBar.show()
                }

                HttpStatus.SUCCESS -> {
                    listTrendingMovies.clear()
                    it.data?.let { videos ->
                        listTrendingMovies.addAll(videos)
                        sliderAdapter!!.notifyDataSetChanged()
                    }

                    linearLayoutDetails.visibility = View.VISIBLE
                    contentLoadingProgressBar.hide()

                }

                HttpStatus.ERROR -> {
                    Toast.makeText(
                        this,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                    contentLoadingProgressBar.hide()
                    linearLayoutDetails.visibility = View.GONE
                }
            }

        })

    }
}