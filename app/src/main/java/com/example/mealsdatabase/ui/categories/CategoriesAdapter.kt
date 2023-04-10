package com.example.mealsdatabase.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsdatabase.R
import com.example.mealsdatabase.data.model.categories.CategoryModel
import com.example.mealsdatabase.databinding.ItemCategoriesBinding

class CategoriesAdapter(val categories: List<CategoryModel>?):
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCategoriesBinding.bind(view)

        fun handleData(item: CategoryModel?) {
            item?.let {
                with(it){
                    binding.tv1.text = strCategory
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categories?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rdModel = categories?.get(position)
        holder.handleData(rdModel)
    }

}
