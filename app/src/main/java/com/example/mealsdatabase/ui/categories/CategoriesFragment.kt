package com.example.mealsdatabase.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsdatabase.data.model.categories.CategoriesModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealsByCategoryModel
import com.example.mealsdatabase.databinding.FragmentCategoriesBinding
import com.facebook.internal.Utility.isNullOrEmpty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private val categoriesViewModel by viewModels<CategoriesViewModel>()
    private var categoriesAdapter: CategoriesAdapter? = null
    private var mealsByCategoryAdapter: MealsByCategoryAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        categoriesViewModel.categories.observe(viewLifecycleOwner){
            it?.let{
                setupUIForCategories(it)
            }
        }

        categoriesViewModel.mealsByCategory.observe(viewLifecycleOwner){
            it?.let {
                setupUIForMealsByCategory(it)
            }
        }

        categoriesViewModel.getCategories()
        categoriesViewModel.getMealsByCategory()

        return binding.root
    }

    private fun setupUIForCategories(categories: CategoriesModel){
        categoriesAdapter = CategoriesAdapter(categories.categories)
        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter

        }
    }

    private fun setupUIForMealsByCategory(meals : MealsByCategoryModel){
        mealsByCategoryAdapter = MealsByCategoryAdapter(meals.meals as List<MealModel>)
        binding.rvMeals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mealsByCategoryAdapter
        }

    }
}