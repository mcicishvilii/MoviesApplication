package com.example.moviesapp.UI.MoviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviesapp.UI.Login.MyPagingSource

class MoviesListViewModel : ViewModel() {
    val userPager = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = { MyPagingSource() }).flow.cachedIn(viewModelScope)
}