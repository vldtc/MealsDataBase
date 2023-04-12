package com.example.mealsdatabase.ui.categories.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsdatabase.data.model.meal.MealModel
import com.example.mealsdatabase.data.model.meal.MealModelX
import com.example.mealsdatabase.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {
    private val _mealName = MutableLiveData<String?>()
    val mealName: LiveData<String?> = _mealName

    private val _mealPic = MutableLiveData<String?>()
    val mealPic: LiveData<String?> = _mealPic



    fun getMeal(mealName: String){
        viewModelScope.launch {
            val result = repository.getMeal(mealName)
            _mealName.postValue(result.strMeal)
            _mealPic.postValue(result.strMealThumb)
        }
    }
}