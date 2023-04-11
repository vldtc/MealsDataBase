package com.example.mealsdatabase.data.model.mealsbycategory


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MealModel(
    @SerializedName("idMeal")
    val idMeal: String? = "",
    @SerializedName("strMeal")
    val strMeal: String? = "",
    @SerializedName("strMealThumb")
    val strMealThumb: String? = ""
) : Serializable