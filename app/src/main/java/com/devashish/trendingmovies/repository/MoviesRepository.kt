package com.devashish.trendingmovies.repository

import com.devashish.trendingmovies.BuildConfig
import com.devashish.trendingmovies.responseparser.TrendingMoviesResponseParser
import com.devashish.trendingmovies.responseparser.VideoDetailsResponseParser
import com.devashish.trendingmovies.restapi.ApiClient
import com.devashish.trendingmovies.restapi.ApiInterface
import com.devashish.trendingmovies.utilities.MediaType
import com.devashish.trendingmovies.utilities.WindowTime

class MoviesRepository {

    private val apiService = ApiClient.createService(ApiInterface::class.java)

    suspend fun getTrendingVideos(): TrendingMoviesResponseParser {
        return apiService.getTrendingVideos(MediaType.ALL, WindowTime.WEEK, BuildConfig.API_KEY)
    }

    suspend fun getDetails(id: Int,mediaType: String): VideoDetailsResponseParser {
        return apiService.getDetails(
            mediaType, id, BuildConfig.API_KEY, "credits"
        )
    }

}