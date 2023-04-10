package com.example.mealsdatabase.ui.login.register

import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mealsdatabase.R
import com.example.mealsdatabase.databinding.FragmentLoginBinding
import com.example.mealsdatabase.databinding.FragmentRegisterBinding
import com.example.mealsdatabase.ui.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding
    private val registerViewModel by viewModels<RegisterViewModel>()
    private lateinit var auth: FirebaseAuth;

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        binding?.btnSignUp?.setOnClickListener {
            registerUser(binding?.etEmail?.text.toString(), binding?.etPassword?.text.toString())
        }

        binding?.btnBack?.setOnClickListener {
            findNavController().navigate(R.id.navigation_login)
        }

        return binding?.root
    }

    private fun registerUser(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    findNavController().navigate(R.id.action_back_to_login)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }


}