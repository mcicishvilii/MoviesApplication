package com.example.moviesapp

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val error: String) : Resource<T>()
    class Loading<T>(val loading: Boolean) : Resource<T>()
}
