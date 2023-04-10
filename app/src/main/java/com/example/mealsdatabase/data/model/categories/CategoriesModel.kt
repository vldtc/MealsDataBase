package com.example.mealsdatabase.data.model.categories


import com.google.gson.annotations.SerializedName

data class CategoriesModel(
    @SerializedName("categories")
    val categories: List<CategoryModel>? = listOf()
)