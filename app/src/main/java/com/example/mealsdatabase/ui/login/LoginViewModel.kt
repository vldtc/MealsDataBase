package com.example.mealsdatabase.ui.login

import androidx.lifecycle.ViewModel
import com.example.mealsdatabase.data.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {
    // TODO: Implement the ViewModel
}