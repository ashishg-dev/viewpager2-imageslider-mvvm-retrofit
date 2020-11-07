package com.devashish.trendingmovies.model

import android.os.Parcel
import android.os.Parcelable

data class TrendingMoviesModel(
    val id: Int,
    val video: Boolean,
    val voteCount: Int,
    val voteAverage: Double,
    val title: String?,
    val releaseDate: String?,
    val originalLanguage: String,
    val originalTitle: String?,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val posterPath: String,
    val popularity: Double,
    val mediaType: String,
    val originalName: String?,
    val name: String?,
    val firstAirDate: String?,
    val originCountry: List<String>?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeInt(voteCount)
        parcel.writeDouble(voteAverage)
        parcel.writeString(title)
        parcel.writeString(releaseDate)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(backdropPath)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
        parcel.writeDouble(popularity)
        parcel.writeString(mediaType)
        parcel.writeString(originalName)
        parcel.writeString(name)
        parcel.writeString(firstAirDate)
        parcel.writeStringList(originCountry)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrendingMoviesModel> {
        override fun createFromParcel(parcel: Parcel): TrendingMoviesModel {
            return TrendingMoviesModel(parcel)
        }

        override fun newArray(size: Int): Array<TrendingMoviesModel?> {
            return arrayOfNulls(size)
        }
    }
}