package com.example.mealsdatabase.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsdatabase.R
import com.example.mealsdatabase.data.model.mealsbycategory.MealModel
import com.example.mealsdatabase.databinding.ItemMealsByCategoryBinding

class MealsByCategoryAdapter(val meals: List<MealModel>?):
    RecyclerView.Adapter<MealsByCategoryAdapter.ViewHolder>(){

    class ViewHolder(val view:View): RecyclerView.ViewHolder(view) {
        val binding = ItemMealsByCategoryBinding.bind(view)

        fun handleData(item: MealModel?){
            item?.let {
                with(it){
                    binding.tv1.text = strMeal
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_meals_by_category, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = meals?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rdModel = meals?.get(position)
        holder.handleData(rdModel)
    }

}
