package com.example.mealsdatabase.data.model.repository

import com.example.mealsdatabase.data.model.categories.CategoriesModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealsByCategoryModel
import com.example.mealsdatabase.data.model.remote.ApiRequest
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
): Repository {

    override suspend fun getCategories(): CategoriesModel = apiRequest.getCategories()

    override suspend fun getMealsByCategory(category: String): MealsByCategoryModel = apiRequest.getMealsByCategories(category)

}