package com.example.moviesapp.UI.MoviesDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviesapp.BaseFragment
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MoviesDetailsFragment : BaseFragment<FragmentMoviesDetailsBinding>(FragmentMoviesDetailsBinding::inflate) {

    override fun viewCreated() {
        binding.tvMovieName.text = arguments?.getString("title")
        binding.tvAverageRatingDetails.text = arguments?.getDouble("voteAverage").toString()
        binding.ivAboutMovie.text = arguments?.getString("about")
        binding.tvReleaseDateDetails.text = arguments?.getString("releaseDate")
        binding.tvRateCountDetails.text = arguments?.getInt("voteCount").toString()
        binding.tvAveragePopularityDetails.text = arguments?.getDouble("popular").toString()
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${arguments?.getString("largePoster")}")
            .into(binding.ivLargePoster)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${arguments?.getString("smallPoster")}")
            .into(binding.ivSmallPoster)

    }

    override fun listeners() {

    }

}