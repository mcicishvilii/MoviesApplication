package com.example.movieswatchlist.Models.Responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class PopularResponse(
    val page:Int?,
    val results: List<Result1>?){
    @Parcelize
    data class Result1(
        @Json (name = "id")
        val id: Int?,
        @Json(name = "original_title")
        val originalTitle: String?,
        @Json(name = "poster_path")
        val posterPath: String?,
        @Json(name = "release_date")
        val releaseDate: String?,
        val title: String?,
        @Json(name = "vote_count")
        val voteCount: Int?
    ):Parcelable
    }


