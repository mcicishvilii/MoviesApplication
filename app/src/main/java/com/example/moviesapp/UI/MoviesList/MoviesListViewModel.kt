package com.example.moviesapp.UI.MoviesList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviesapp.Models.UserModel
import com.example.moviesapp.Network.RetrofitHelper
import com.example.moviesapp.Resource
import com.example.moviesapp.UI.Login.MyPagingSource
import com.example.moviesapp.UI.Login.apiKeyXelit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {

    val userPager = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = { MyPagingSource() }).flow.cachedIn(viewModelScope)

}