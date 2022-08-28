package com.example.moviesapp.UI.Register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.BaseFragment
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private lateinit var auth: FirebaseAuth
    override fun viewCreated() {
        auth = FirebaseAuth.getInstance()
    }

    override fun listeners() {
        binding.btnRegister.setOnClickListener {
            registerUser1()
        }
    }

    private fun registerUser1() {
        val email = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(),
                            "registered user: ${auth.currentUser?.email}",
                            Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_registerFragment_to_welcomeFragment)
                    }
                } catch (e: Exception) {
                }
            }
        } else {
            Toast.makeText(requireContext(), "not correct e-mail format!", Toast.LENGTH_SHORT)
                .show()
        }
    }


}