package com.example.mealsdatabase.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsdatabase.R
import com.example.mealsdatabase.data.model.categories.CategoriesModel
import com.example.mealsdatabase.data.model.categories.CategoryModel
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        categoriesViewModel.categories.observe(viewLifecycleOwner){
            it?.let {
                setupUI(it)
            }
        }

        categoriesViewModel.getCateogries()
        return binding.root
    }

    fun setupUI(categories: CategoriesModel){
        categoriesAdapter = CategoriesAdapter(categories.categories as List<CategoryModel>)
        binding.rvMeals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoriesAdapter
        }

        categoriesAdapter!!.onItemClick = {
            val bundle = Bundle().apply {
                putSerializable("CategoryItem", it)
            }
            findNavController().navigate(
                R.id.action_category_meals,bundle
            )
        }
    }



}