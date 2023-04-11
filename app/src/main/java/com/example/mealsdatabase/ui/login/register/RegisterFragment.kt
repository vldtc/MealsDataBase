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

        val email = binding?.etEmail?.text
        val password = binding?.etPassword?.text
        val passwordConfirm = binding?.etPasswordConfirm?.text

        binding?.btnSignUp?.setOnClickListener {
            if (registerViewModel.checkEmail(email.toString()) and registerViewModel.checkPassword(
                    password.toString()

                ) and registerViewModel.checkPasswordConfirm(passwordConfirm.toString())
            ) {
                if(registerViewModel.isEmailValid(email.toString())){
                    if(registerViewModel.passwordsMatch(password.toString(), passwordConfirm.toString())){
                        registerUser(binding?.etEmail?.text.toString(), binding?.etPassword?.text.toString())
                    }
                    else{
                        binding?.etPassword?.setError(registerViewModel.errorMessagePass.value)
                        binding?.etPasswordConfirm?.setError(registerViewModel.errorMessagePassConfirm.value)
                    }
                }else{
                    binding?.etEmail?.setError(registerViewModel.errorMessageEmail.value )
                }

            }else {
                if(!registerViewModel.checkEmail(email.toString())) binding?.etEmail?.setError(registerViewModel.errorMessageEmail.value)
                if(!registerViewModel.checkPassword(password.toString())) binding?.etPassword?.setError(registerViewModel.errorMessagePass.value)
                if(!registerViewModel.checkPasswordConfirm(passwordConfirm.toString())) binding?.etPasswordConfirm?.setError(registerViewModel.errorMessagePassConfirm.value)
            }

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
                    Toast.makeText(
                        context, "Authentication successful. You can sign in now!.",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.navigation_login)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }
            }
    }


}