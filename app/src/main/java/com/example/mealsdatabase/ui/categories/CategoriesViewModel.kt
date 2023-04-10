package com.example.mealsdatabase.ui.categories

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.mealsdatabase.data.model.categories.CategoriesModel
import com.example.mealsdatabase.data.model.categories.CategoryModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealsByCategoryModel
import com.example.mealsdatabase.data.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    val categories = MutableLiveData<CategoriesModel>()
    val mealsByCategory = MutableLiveData<MealsByCategoryModel>()
    private val list = listOf(
        "Beef",
        "Breakfast",
        "Chicken",
        "Dessert",
        "Goat",
        "Lamb",
        "Miscellaneous",
        "Pasta",
        "Pork",
        "Seafood",
        "Side",
        "Starter",
        "Vegan",
        "Vegetarian"
    )

    fun getCategories() {
        viewModelScope.launch {
            val result = repository.getCategories()
            categories.postValue(result)
        }
    }

    fun getMealsByCategory() {
        viewModelScope.launch {
            val result = repository.getMealsByCategory(list[0])
            mealsByCategory.postValue(result)
        }
    }

}

