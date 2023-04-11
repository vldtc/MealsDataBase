package com.example.mealsdatabase.data.repository

import com.example.mealsdatabase.data.model.categories.CategoriesModel
import com.example.mealsdatabase.data.model.categories.CategoryModel
import com.example.mealsdatabase.data.model.meal.MealModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealsByCategoryModel

interface Repository {

    suspend fun getCategories(): CategoriesModel

    suspend fun getMealsByCategory(category: String?): MealsByCategoryModel

    suspend fun getMeal(meal: String?) : MealModel
}