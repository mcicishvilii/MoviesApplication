package com.example.moviesapp.UI.MoviesList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.Adapters.MoviesAdapter
import com.example.moviesapp.BaseFragment
import com.example.moviesapp.Models.UserModel
import com.example.moviesapp.R
import com.example.moviesapp.Resource
import com.example.moviesapp.databinding.FragmentMoviesListBinding
import com.example.movieswatchlist.Models.Responses.PopularResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesListFragment :
    BaseFragment<FragmentMoviesListBinding>(FragmentMoviesListBinding::inflate) {

    private lateinit var auth: FirebaseAuth

    private val moviesListViewModel: MoviesListViewModel by viewModels()
    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter() }

    override fun viewCreated() {
        auth = FirebaseAuth.getInstance()
        checkLoggedInState()
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesListViewModel.userPager.collect {
                    moviesAdapter.submitData(it)
                }
            }
        }
    }

    override fun listeners() {
        moviesAdapter.apply {
            setOnItemClickListener { item, _ ->
                val bundle = bundleOf(
                    "title" to item.originalTitle,
                    "about" to item.overview,
                    "largePoster" to item.backdropPath,
                    "smallPoster" to item.posterPath,
                    "voteAverage" to item.voteAverage,
                    "voteCount" to item.voteCount,
                    "releaseDate" to item.releaseDate,
                    "popular" to item.popularity
                )
                findNavController().navigate(
                    R.id.action_moviesListFragment_to_moviesDetailsFragment,
                    bundle
                )
            }
        }
    }

    private fun setupRecycler() {
        binding.rvMovies.apply {
            adapter = moviesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false)
        }
    }

    private fun checkLoggedInState() {
        val user = auth.currentUser
        if (user == null) {
            binding.tvFindYourMovie.text = "notLoggedIn"
        } else {
            binding.tvFindYourMovie.text = user.email
            binding.btnLogOut.setOnClickListener {
                auth.signOut()
                findNavController().navigate(R.id.action_moviesListFragment_to_welcomeFragment)
            }
        }
    }



}
