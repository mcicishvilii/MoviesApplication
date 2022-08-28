package com.example.moviesapp.UI.MoviesDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapp.BaseFragment
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesDetailsBinding

class MoviesDetailsFragment : BaseFragment<FragmentMoviesDetailsBinding>(FragmentMoviesDetailsBinding::inflate) {

//    val args:MoviesDetailsFragmentArgs by navArgs()

    override fun viewCreated() {
//        val mishoargs = args.movieInfo
//        binding.tvMovieName.text = mishoargs
    }

    override fun listeners() {

    }

}