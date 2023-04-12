package com.example.mealsdatabase.ui.categories.meal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import coil.load
import com.example.mealsdatabase.R
import com.example.mealsdatabase.data.model.meal.MealModelX
import com.example.mealsdatabase.data.model.mealsbycategory.MealModel
import com.example.mealsdatabase.databinding.FragmentCategoriesBinding
import com.example.mealsdatabase.databinding.FragmentMealBinding
import com.example.mealsdatabase.ui.categories.CategoriesAdapter
import com.example.mealsdatabase.ui.categories.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealFragment : Fragment() {

    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!
    private val mealViewModel by viewModels<MealViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealBinding.inflate(inflater, container, false)

        val meal = arguments?.getSerializable("Meal") as MealModel
        mealViewModel.getMeal(meal.strMeal.toString())

        val tvName: TextView = binding.tvName
        mealViewModel.mealName.observe(viewLifecycleOwner){
            tvName.text = it
        }

        val ivImage: ImageView = binding.sivCategory
        mealViewModel.mealPic.observe(viewLifecycleOwner){
            ivImage.load(
                data = "$it"
            )
        }

        return binding.root
    }

}