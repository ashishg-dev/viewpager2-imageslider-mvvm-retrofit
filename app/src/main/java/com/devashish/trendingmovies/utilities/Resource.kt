package com.devashish.trendingmovies.utilities

data class Resource<out T>(val status: HttpStatus, val data: T?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(HttpStatus.SUCCESS, data)
        }

        fun <T> error(data: T?): Resource<T> {
            return Resource(HttpStatus.ERROR, data)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(HttpStatus.LOADING, data)
        }

    }

}