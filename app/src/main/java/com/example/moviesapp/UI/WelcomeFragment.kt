package com.example.moviesapp.UI

import android.net.Uri
import android.widget.MediaController
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

        var videoUrl = "https://imgur.com/f62iZZL.mp4"
        val uri: Uri = Uri.parse(videoUrl)
        binding.vvVideo.setVideoURI(uri)
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.vvVideo)
        mediaController.setMediaPlayer(binding.vvVideo)
        binding.vvVideo.setMediaController(mediaController)
        binding.vvVideo.start()
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