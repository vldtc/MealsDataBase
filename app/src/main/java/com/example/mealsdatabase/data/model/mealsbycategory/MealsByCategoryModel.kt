package com.example.mealsdatabase.data.model.mealsbycategory


import com.google.gson.annotations.SerializedName

data class MealsByCategoryModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)