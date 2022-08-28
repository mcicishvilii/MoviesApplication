package com.example.moviesapp.Models.Requests

import com.squareup.moshi.Json

data class LoginRequest(
    @Json(name = "request_token")
    val requestToken:String,
)
