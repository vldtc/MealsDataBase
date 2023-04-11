package com.example.mealsdatabase.ui.login.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealsdatabase.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.util.Patterns

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    // LiveData for error messages
    private val _errorMessageEmail = MutableLiveData<String>()
    val errorMessageEmail: LiveData<String> get() = _errorMessageEmail
    private val _errorMessagePass = MutableLiveData<String>()
    val errorMessagePass: LiveData<String> get() = _errorMessagePass
    private val _errorMessagePassConfirm = MutableLiveData<String>()
    val errorMessagePassConfirm: LiveData<String> get() = _errorMessagePassConfirm

    fun checkEmail(email: String): Boolean {
        return if (email.isNotEmpty()) {
            true
        }else{
            _errorMessageEmail.value = "Email is required."
            false
        }
    }
    fun isEmailValid(email: String): Boolean {
        return if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) true
        else {
            _errorMessageEmail.value = "Use a valid email address."
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
    fun checkPasswordConfirm(passwordConfirm: String): Boolean {
        return if (passwordConfirm.isNotEmpty()) {
            true
        } else{
            _errorMessagePassConfirm.value = "Password Confirmation is required."
            false
        }
    }

    fun passwordsMatch(password:String, passwordConfirm: String): Boolean{
        return if (password == passwordConfirm) true
        else{
            _errorMessagePass.value = "Passwords do not match! Try again"
            _errorMessagePassConfirm.value = "Passwords do not match! Try again"
            false
        }
    }


}