package com.example.mealsdatabase.ui.categories.categoriesmeals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsdatabase.R
import com.example.mealsdatabase.data.model.mealsbycategory.MealModel
import com.example.mealsdatabase.databinding.ItemMealsBinding

class CategoriesMealsAdapter(val mealModels: List<MealModel>?):
    RecyclerView.Adapter<CategoriesMealsAdapter.ViewHolder>(){

    var onItemClick: ((MealModel) -> Unit)? = null

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemMealsBinding.bind(view)

        fun handleData(item: MealModel?){
            item?.let {
                with(it){
                    binding.tvName.text = strMeal
                    binding.sivCategory.load(
                        data="$strMealThumb"
                    ){
                        listener{request, result ->
                            binding.productImageViewLoadingProgressBar.isGone = true
                        }
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_meals, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mealModels?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rdModel = mealModels?.get(position)
        holder.handleData(rdModel)
        holder.itemView.setOnClickListener {
            mealModels?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }
    }

}
