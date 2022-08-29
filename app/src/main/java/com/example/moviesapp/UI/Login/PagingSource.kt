package com.example.moviesapp.UI.Login

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.Network.RetrofitHelper
import com.example.movieswatchlist.Models.Responses.PopularResponse


private const val STARTING_PAGE_INDEX = 1
val apiKeyXelit = "acdbc7ef61877f0d6b3e29d062218ccc"

class MyPagingSource : PagingSource<Int, PopularResponse.Result1>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,PopularResponse.Result1> {
        val pageIndex: Int = params.key ?: STARTING_PAGE_INDEX
        val prevPage = params.key
        val nextPage = pageIndex + 1
        val response =
            RetrofitHelper.popularsService.getPopularMovies(apiKeyXelit,pageIndex)
        Log.d("tag", "${response.body()} response")
        return try {
            if (response.isSuccessful) {
                val movies = response.body()
                Log.d("tag", movies?.results.toString())
                return LoadResult.Page((movies!!.results!!), prevPage, nextPage)
            } else {
                LoadResult.Error(Throwable())
            }

        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularResponse.Result1>): Int? {
        return null
    }
}

