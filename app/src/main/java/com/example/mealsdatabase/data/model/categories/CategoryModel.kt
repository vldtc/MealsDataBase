package com.example.mealsdatabase.data.model.categories


import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("idCategory")
    val idCategory: String? = "",
    @SerializedName("strCategory")
    val strCategory: String? = "",
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String? = "",
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String? = ""
)