package com.example.mealsdatabase.data.repository

import com.example.mealsdatabase.data.model.categories.CategoriesModel
import com.example.mealsdatabase.data.model.categories.CategoryModel
import com.example.mealsdatabase.data.model.meal.MealModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealsByCategoryModel
import com.example.mealsdatabase.data.remote.ApiRequest
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
): Repository {

    override suspend fun getCategories(): CategoriesModel = apiRequest.getCategories()

    override suspend fun getMealsByCategory(category: String?): MealsByCategoryModel = apiRequest.getMealsByCategories(category)

    override suspend fun getMeal(meal: String?): MealModel = apiRequest.getMeal(meal)

}