package com.devashish.trendingmovies.responseparser

import com.google.gson.annotations.SerializedName

data class VideoDetailsResponseParser(
    @SerializedName("credits") val credits: Credits
)

data class Credits(
    @SerializedName("cast") val cast: List<Cast>
)

data class Cast(

    @SerializedName("character") val character: String,
    @SerializedName("credit_id") val credit_id: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: Int,
    @SerializedName("profile_path") val profile_path: String?,
    @SerializedName("order") val order: Int
)