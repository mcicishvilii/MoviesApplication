package com.example.moviesapp.Network

import com.example.movieswatchlist.Models.Responses.PopularResponse

import org.intellij.lang.annotations.Language
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularsApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page:Int
        ): Response<PopularResponse>
}