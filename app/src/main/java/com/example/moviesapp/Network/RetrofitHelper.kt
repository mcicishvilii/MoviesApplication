package com.example.moviesapp.Network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val RetrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    val tokenService by lazy {
        RetrofitBuilder.create(LoginApi::class.java)
    }

    val popularsService by lazy {
        RetrofitBuilder.create(PopularsApi::class.java)
    }

}