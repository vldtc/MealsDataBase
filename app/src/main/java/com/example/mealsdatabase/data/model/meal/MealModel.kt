package com.example.mealsdatabase.data.model.meal


import com.google.gson.annotations.SerializedName

data class MealModel(
    @SerializedName("meals")
    val meals: List<MealModelX>? = listOf()
)