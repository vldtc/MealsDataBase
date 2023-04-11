package com.example.mealsdatabase.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsdatabase.R
import com.example.mealsdatabase.data.model.categories.CategoryModel
import com.example.mealsdatabase.databinding.ItemCategoriesBinding

class CategoriesAdapter(val categories: List<CategoryModel>?):
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var onItemClick: ((CategoryModel) -> Unit)? = null
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCategoriesBinding.bind(view)

        fun handleData(item: CategoryModel?) {
            item?.let {
                with(it){
                    binding.tvName.text = strCategory
                    binding.sivCategory.load(
                        data = "$strCategoryThumb"
                    ){
                        listener { request, result ->
                            binding.productImageViewLoadingProgressBar.isGone = true
                        }
                    }
                    binding.tvDescription.text = strCategoryDescription
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
        holder.itemView.setOnClickListener{
            categories?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }
    }

}
