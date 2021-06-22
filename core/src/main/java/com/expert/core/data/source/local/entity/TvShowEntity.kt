package com.expert.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshow")
data class TvShowEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId:String,

    @ColumnInfo(name = "TvShowTitle")
    var tvShowTitle:String,

    @ColumnInfo(name = "overview")
    var overview:String,

    @ColumnInfo(name = "posterPath")
    var posterPath:String,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate:String,

    @ColumnInfo(name = "voteAverage")
    var voteAverage:String,

    @ColumnInfo(name = "favorited")
    var favorited:Boolean = false
)