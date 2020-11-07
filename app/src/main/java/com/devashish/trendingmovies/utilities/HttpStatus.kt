package com.devashish.trendingmovies.utilities

sealed class HttpStatus {
    object SUCCESS : HttpStatus()
    object ERROR : HttpStatus()
    object LOADING : HttpStatus()
}