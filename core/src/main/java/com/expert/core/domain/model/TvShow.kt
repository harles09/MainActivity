package com.expert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow (
    val tvShowId:String,
    val tvShowTitle:String,
    val overview:String,
    val posterPath:String,
    val firstAirDate:String,
    val voteAverage:String,
    var favorited:Boolean
        ):Parcelable