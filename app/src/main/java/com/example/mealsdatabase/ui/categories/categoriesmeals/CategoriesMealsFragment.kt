package com.example.mealsdatabase.ui.categories.categoriesmeals

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsdatabase.R
import com.example.mealsdatabase.data.model.categories.CategoryModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealModel
import com.example.mealsdatabase.data.model.mealsbycategory.MealsByCategoryModel
import com.example.mealsdatabase.databinding.FragmentCategoriesBinding
import com.example.mealsdatabase.databinding.FragmentCategoriesMealsBinding
import com.example.mealsdatabase.ui.categories.CategoriesAdapter
import com.example.mealsdatabase.ui.categories.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesMealsFragment : Fragment() {

    private var _binding: FragmentCategoriesMealsBinding? = null
    private val binding get() = _binding!!
    private val categoriesMealsViewModel by viewModels<CategoriesMealsViewModel>()
    private var categoriesMealsAdapter: CategoriesMealsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesMealsBinding.inflate(inflater, container, false)

        val meals = arguments?.getSerializable("CategoryItem") as CategoryModel

        categoriesMealsViewModel.meals.observe(viewLifecycleOwner){
            it?.let {
                setupUI(it)
            }
        }

        categoriesMealsViewModel.getMeals(meals.strCategory.toString())
        return binding.root
    }

    fun setupUI(meals: MealsByCategoryModel){
        categoriesMealsAdapter = CategoriesMealsAdapter(meals.meals as List<MealModel>)
        binding.rvMeals.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = categoriesMealsAdapter
        }

        categoriesMealsAdapter!!.onItemClick = {
            val bundle = Bundle().apply {
                putSerializable("Meal", it)
            }
            findNavController().navigate(
                R.id.action_meal, bundle
            )
        }
    }
}