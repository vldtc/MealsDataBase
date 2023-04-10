package com.example.mealsdatabase.data.model.remote

import com.example.mealsdatabase.data.model.categories.CategoriesModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealsByCategoryModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET(ApiDetails.CATEGORIES)
    suspend fun getCategories(): CategoriesModel

    @GET(ApiDetails.BY_CATEGORIES)
    suspend fun getMealsByCategories(
        @Query("c") category: String
    ): MealsByCategoryModel

}