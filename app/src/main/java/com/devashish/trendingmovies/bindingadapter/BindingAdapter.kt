package com.devashish.trendingmovies.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.devashish.trendingmovies.BuildConfig
import com.devashish.trendingmovies.utilities.MediaType
import com.devashish.trendingmovies.utilities.Utility
import com.squareup.picasso.Picasso
import java.util.*

@BindingAdapter("app:setURLImage")
fun setURLImage(view: ImageView, url: String) {
    Picasso.get().load(BuildConfig.IMAGE_BASE_URL + url).into(view)
}

@BindingAdapter(value = ["mediaType", "originalTitle", "originalName"])
fun setTitle(view: TextView, mediaType: String?, originalTitle: String?, originalName: String?) {
    if (mediaType != null && (originalTitle != null || originalName != null)) {
        when (MediaType.valueOf(mediaType.toUpperCase(Locale.ROOT))) {

            MediaType.MOVIE -> {
                view.text = originalTitle
            }

            MediaType.TV -> {
                view.text = originalName
            }

            else -> {
                view.text = ""
            }

        }

    } else {
        view.text = ""
    }

}

@BindingAdapter(value = ["mediaType", "releaseDate", "firstAirDate"])
fun setDate(view: TextView, mediaType: String?, releaseDate: String?, firstAirDate: String?) {
    if (mediaType != null && (releaseDate != null || firstAirDate != null)) {
        when (MediaType.valueOf(mediaType.toUpperCase(Locale.ROOT))) {

            MediaType.MOVIE -> {
                view.text = Utility.getFormattedDate(releaseDate!!)
            }

            MediaType.TV -> {
                view.text = Utility.getFormattedDate(firstAirDate!!)
            }

            else -> {
                view.text = ""
            }

        }
    } else {
        view.text = ""
    }
}