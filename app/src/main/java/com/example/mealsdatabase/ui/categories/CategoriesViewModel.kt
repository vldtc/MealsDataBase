package com.example.mealsdatabase.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsdatabase.data.model.categories.CategoriesModel
import com.example.mealsdatabase.data.model.categories.CategoryModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealsByCategoryModel
import com.example.mealsdatabase.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    val categories = MutableLiveData<CategoriesModel>()

    fun getCateogries(){
        viewModelScope.launch {
            val result = repository.getCategories()
            categories.postValue(result)
        }
    }
}

