package com.example.mealsdatabase.ui.login

import android.os.Build.VERSION_CODES.P
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealsdatabase.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    // LiveData for error messages
    private val _errorMessageEmail = MutableLiveData<String>()
    val errorMessageEmail: LiveData<String> get() = _errorMessageEmail
    private val _errorMessagePass = MutableLiveData<String>()
    val errorMessagePass: LiveData<String> get() = _errorMessagePass

    // Function to perform email and password validation
    fun checkEmail(email: String): Boolean {
        return if (email.isNotEmpty()) {
            true
        }else{
            _errorMessageEmail.value = "Email is required."
            false
        }
    }
    fun checkPassword(password: String): Boolean {
        return if (password.isNotEmpty()) {
            true
        } else{
            _errorMessagePass.value = "Password is required."
            false
        }
    }

}