package com.expert.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId:String,

    @ColumnInfo(name = "movieTitle")
    var movieTitle:String,

    @ColumnInfo(name = "overview")
    var overview:String,

    @ColumnInfo(name = "posterPath")
    var posterPath:String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate:String = "",

    @ColumnInfo(name = "voteAverage")
    var voteAverage:String,

    @ColumnInfo(name = "favorited")
    var favorited:Boolean = false
)