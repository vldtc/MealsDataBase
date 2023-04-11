package com.example.mealsdatabase.ui.categories.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsdatabase.data.model.meal.MealModel
import com.example.mealsdatabase.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    private val _meal = MutableLiveData<MealModel>()
    val meal : LiveData<MealModel> get() = _meal

    fun getMeal(mealName: String){
        viewModelScope.launch {
            val result = repository.getMeal(mealName)
            _meal.postValue(result)
        }
    }
}