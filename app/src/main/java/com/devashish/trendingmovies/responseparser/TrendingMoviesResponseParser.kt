package com.devashish.trendingmovies.responseparser

import com.google.gson.annotations.SerializedName

data class TrendingMoviesResponseParser(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Results>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
)

data class Results(
    @SerializedName("id") val id: Int,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_count") val vote_count: Int,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("genre_ids") val genre_ids: List<Int>,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("media_type") val media_type: String,
    @SerializedName("original_name") val original_name: String,
    @SerializedName("name") val name: String,
    @SerializedName("first_air_date") val first_air_date: String,
    @SerializedName("origin_country") val origin_country: List<String>
)