package com.example.mealsdatabase.ui.login.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealsdatabase.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to MealsDB.\n Head over to categories to view our entire range of meals!"
    }
    val text: LiveData<String> = _text
}