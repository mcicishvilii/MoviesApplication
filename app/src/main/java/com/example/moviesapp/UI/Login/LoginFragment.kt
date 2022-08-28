package com.example.moviesapp.UI.Login

import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.BaseFragment
import com.example.moviesapp.Models.UserModel
import com.example.moviesapp.R
import com.example.moviesapp.Resource.*
import com.example.moviesapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    val apiKeyXelit = "acdbc7ef61877f0d6b3e29d062218ccc"
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth


    override fun viewCreated() {
        auth = FirebaseAuth.getInstance()
//        checkLoggedInState()
        binding.etUsername.setText("mcicishvilii")
        binding.etPassword.setText("Suckartwell0!")

    }



    override fun listeners() {
        binding.btnSkipButton.setOnClickListener {
            loginWithUser()
        }

        binding.btnLogin.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                loginViewModel.logIn(userModel = UserModel(
                    binding.etUsername.text.toString(),
                    binding.etPassword.text.toString(),
                    loginViewModel.tokenResponse(apiKeyXelit)
                )
                ).collectLatest {
                    when (it) {
                        is Success -> {
                            Log.d("tag", it.data)
                            findNavController().navigate(R.id.action_loginFragment_to_moviesListFragment)
                        }
                        is Error -> {
                            Toast.makeText(requireContext(), "oops error", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("tag", "error ${it.error}")
                        }
                        is Loading -> {
                            Log.d("tag", "loading")
                        }
                    }
                }
            }
        }
    }



    private fun loginWithUser(){
        val email = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main){
                        checkLoggedInState()
                        findNavController().navigate(R.id.action_loginFragment_to_moviesListFragment)
                        Toast.makeText(requireContext(),"logged in dear ${auth.currentUser}!", Toast.LENGTH_SHORT).show()
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(),"wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun checkLoggedInState() {
        val user = auth.currentUser
        if (user == null) {
            binding.loginTitle.text = "notLoggedIn"
        } else {
            binding.loginTitle.setTextColor(Color.RED)
        }
    }

}
