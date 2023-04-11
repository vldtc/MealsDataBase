package com.example.mealsdatabase.ui.categories.categoriesmeals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsdatabase.data.model.mealsbycategory.MealsByCategoryModel
import com.example.mealsdatabase.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesMealsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val meals = MutableLiveData<MealsByCategoryModel>()

    fun getMeals(category: String){
        viewModelScope.launch {
            val result = repository.getMealsByCategory(category)
            meals.postValue(result)
        }
    }
}