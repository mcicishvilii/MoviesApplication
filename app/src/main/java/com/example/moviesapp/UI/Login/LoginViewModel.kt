package com.example.moviesapp.UI.Login

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.viewModelScope
import com.example.moviesapp.Models.UserModel
import com.example.moviesapp.Network.RetrofitHelper
import com.example.moviesapp.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val apiKeyXelit = "acdbc7ef61877f0d6b3e29d062218ccc"
    private var token = ""

    init {
        viewModelScope.launch {
            token = tokenResponse(apiKeyXelit)
        }
    }

    suspend fun tokenResponse(apiKey: String): String {
        val response = RetrofitHelper.tokenService.getRequestToken(apiKey)
        return response.body()!!.requestToken
    }


    suspend fun logIn(userModel: UserModel) = flow {
        val response = RetrofitHelper.tokenService.logIn(userModel, apiKeyXelit)
        if (response.isSuccessful) {
            emit(Resource.Success(response.body()?.requestToken!!))
        } else {
            emit(Resource.Error(response.errorBody()?.string() ?: ""))
        }
    }
}