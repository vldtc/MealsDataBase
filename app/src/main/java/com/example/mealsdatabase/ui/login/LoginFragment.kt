package com.example.mealsdatabase.ui.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mealsdatabase.R
import com.example.mealsdatabase.databinding.FragmentLoginBinding
import com.facebook.*
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
@Suppress("DEPRECATION")
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private val loginViewModel by viewModels<LoginViewModel>()

        //Firebase
    private lateinit var auth: FirebaseAuth

        //Google
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)


            //Firebase Initialisation
        auth = Firebase.auth

            //Facebook
        FacebookSdk.sdkInitialize(getApplicationContext())

        // Callback registration
        val callbackManager = CallbackManager.Factory.create()

        binding?.btnFacebook?.setOnClickListener {

            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))
            LoginManager.getInstance().registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(result: LoginResult) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    actionLoggedIn()
                }

            })
        }

            //Google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity() , gso)


        binding?.btnGoogle?.setOnClickListener {
            signInGoogle()
        }

        val email = binding?.etEmail?.text
        val password =  binding?.etPassword?.text

        binding?.btnSignIn?.setOnClickListener {
            if(loginViewModel.checkEmail(email.toString()) and loginViewModel.checkPassword(password.toString())){
                loginUser(binding?.etEmail?.text.toString(), binding?.etPassword?.text.toString())
            }else{
                if(!loginViewModel.checkEmail(email.toString())) binding?.etEmail?.setError(loginViewModel.errorMessageEmail.value)
                if(!loginViewModel.checkPassword(password.toString())) binding?.etPassword?.setError(loginViewModel.errorMessagePass.value)
            }

        }

        binding?.btnSignUp?.setOnClickListener {
            findNavController().navigate(R.id.action_register_form)
        }


        return binding?.root
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            actionLoggedIn()
        }
    }

    private fun loginUser(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    actionLoggedIn()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun actionLoggedIn() {
        findNavController().navigate(R.id.action_logged_in)
    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(context, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                actionLoggedIn()
            }else{
                Toast.makeText(context, it.exception.toString() , Toast.LENGTH_SHORT).show()

            }
        }
    }
}


