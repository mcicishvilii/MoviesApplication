package com.example.moviesapp.Network

import com.example.moviesapp.Models.TokenResponse
import com.example.moviesapp.Models.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {
    @GET("authentication/token/new")
    suspend fun getRequestToken(
        @Query("api_key")
        apiKey: String,
    ): Response<TokenResponse>

    @POST("authentication/token/validate_with_login")
    suspend fun logIn(
        @Body
        userModel: UserModel,
        @Query("api_key")
        apiKey: String,
    ): Response<TokenResponse>
}