package com.devashish.trendingmovies.restapi

import com.devashish.trendingmovies.responseparser.TrendingMoviesResponseParser
import com.devashish.trendingmovies.responseparser.VideoDetailsResponseParser
import com.devashish.trendingmovies.utilities.MediaType
import com.devashish.trendingmovies.utilities.WindowTime
import retrofit2.http.*

interface ApiInterface {

    @GET("/3/trending/{media_type}/{time_window}")
    suspend fun getTrendingVideos(
        @Path("media_type") mediaType: MediaType,
        @Path("time_window") timeWindow: WindowTime,
        @Query("api_key") key: String
    ): TrendingMoviesResponseParser

    @GET("/3/{media_type}/{id}")
    suspend fun getDetails(
        @Path("media_type") mediaType: String,
        @Path("id") id: Int,
        @Query("api_key") key: String,
        @Query("append_to_response") appendToResponse: String
    ): VideoDetailsResponseParser


}