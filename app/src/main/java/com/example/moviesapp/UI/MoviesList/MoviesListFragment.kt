package com.example.moviesapp.UI.MoviesList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.Adapters.MoviesAdapter
import com.example.moviesapp.BaseFragment
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesListBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MoviesListFragment : BaseFragment<FragmentMoviesListBinding>(FragmentMoviesListBinding::inflate) {


    private lateinit var auth: FirebaseAuth

    private val moviesListViewModel: MoviesListViewModel by viewModels()
    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter() }

    override fun viewCreated() {

        auth = FirebaseAuth.getInstance()
        checkLoggedInState()
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                moviesListViewModel.userPager.collect{
                    moviesAdapter.submitData(it)
                }
            }
        }
    }

    override fun listeners() {
//        moviesAdapter.apply {
//            setOnItemClickListener{it,_->
//                val action = MoviesListFragmentDirections.
//                actionMoviesListFragmentToMoviesDetailsFragment(
//                    it.originalTitle
//                )
//                findNavController().navigate(action)
//            }
//        }
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
            binding.btnLogOut.setOnClickListener{
                auth.signOut()
                findNavController().navigate(R.id.action_moviesListFragment_to_welcomeFragment)
            }
        }
    }





}