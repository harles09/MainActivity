package com.expert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val movieId:String,
    val movieTitle:String,
    val overview:String,
    val posterPath:String,
    val releaseDate:String,
    val voteAverage:String,
    val favorited:Boolean
):Parcelable