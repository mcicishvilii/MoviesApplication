package com.example.moviesapp.UI

import androidx.navigation.fragment.findNavController
import com.example.moviesapp.BaseFragment
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentWelcomeBinding
import com.google.firebase.auth.FirebaseAuth

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    private lateinit var auth: FirebaseAuth

    override fun viewCreated() {
        auth = FirebaseAuth.getInstance()
        checkLoggedInState()
    }

    override fun listeners() {

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_registerFragment)
        }
    }

    private fun checkLoggedInState() {
        val user = auth.currentUser
        if (user == null) {

        } else {
            findNavController().navigate(R.id.action_welcomeFragment_to_moviesListFragment)
        }
    }
}