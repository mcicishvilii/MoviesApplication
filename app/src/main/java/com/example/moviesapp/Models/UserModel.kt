package com.example.moviesapp.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class UserModel(
    val username:String?,
    val password:String?,
    @Json(name = "request_token")
    val requestToken:String?
)
