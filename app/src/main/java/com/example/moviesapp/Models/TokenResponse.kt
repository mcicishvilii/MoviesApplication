package com.example.moviesapp.Models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class TokenResponse(
    val success: Boolean,
    @Json(name ="expires_at")
    val expiresAt:String,
    @Json(name = "request_token")
    val requestToken:String
)
