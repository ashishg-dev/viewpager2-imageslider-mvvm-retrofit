package com.devashish.trendingmovies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devashish.trendingmovies.model.CastModel
import com.devashish.trendingmovies.model.TrendingMoviesModel
import com.devashish.trendingmovies.repository.MoviesRepository
import com.devashish.trendingmovies.utilities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class TrendingMoviesViewModel : ViewModel() {

    private val repository = MoviesRepository()
    private val responseVideos = MutableLiveData<Resource<ArrayList<TrendingMoviesModel>>>()
    private val responseVideoDetails = MutableLiveData<Resource<ArrayList<CastModel>>>()

    fun getTrendingVideos(): MutableLiveData<Resource<ArrayList<TrendingMoviesModel>>> {
        responseVideos.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTrendingVideos()
                val trendingMoviesModels = ArrayList<TrendingMoviesModel>()
                if (response.results.isNotEmpty()) {
                    response.results.forEach { results ->
                        trendingMoviesModels.add(
                            TrendingMoviesModel(
                                results.id,
                                results.video,
                                results.vote_count,
                                results.vote_average,
                                results.title,
                                results.release_date,
                                results.original_language,
                                results.original_title,
                                results.backdrop_path,
                                results.adult,
                                results.overview,
                                results.poster_path,
                                results.popularity,
                                results.media_type,
                                results.original_name,
                                results.name,
                                results.first_air_date,
                                results.origin_country
                            )
                        )
                    }
                }
                responseVideos.postValue(Resource.success(trendingMoviesModels))
            } catch (e: Exception) {
                Log.d("exception", e.message.toString())
                responseVideos.postValue(Resource.error(null))
            }

        }

        return responseVideos

    }

    fun getVideoDetails(
        id: Int, mediaType: String
    ): MutableLiveData<Resource<ArrayList<CastModel>>> {
        responseVideoDetails.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getDetails(id, mediaType)
                val casts = ArrayList<CastModel>()
                if (response.credits.cast.isNotEmpty()) {
                    response.credits.cast.forEach { model ->
                        if (model.profile_path != null) {
                            casts.add(CastModel(model.name, model.profile_path))
                        }

                    }
                }
                responseVideoDetails.postValue(Resource.success(casts))
            } catch (e: Exception) {
                Log.d("exception", e.message.toString())
                responseVideoDetails.postValue(Resource.error(null))
            }

        }

        return responseVideoDetails
    }


}