package com.example.taipeizoointroduction.areaDeatil

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeizoointroduction.api.ZooApi
import com.example.taipeizoointroduction.data.ViewItems
import com.example.taipeizoointroduction.data.animal.Animal
import com.example.taipeizoointroduction.data.home.Results
import kotlinx.coroutines.launch

class AreaDetailViewModel : ViewModel() {

    var selectedArea: Results? = null
    private val _formattedExhibitInfo = MutableLiveData<MutableList<ViewItems>>()
    val formattedExhibitInfo: LiveData<MutableList<ViewItems>>
        get() = _formattedExhibitInfo

    init {
        fetchAnimalsByArea()
    }

    private fun fetchAnimalsByArea() {
        viewModelScope.launch {
            try {
                val result = ZooApi.retrofitApiService.getAnimals().result
                _formattedExhibitInfo.value = formatInfoData(result)
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }

    private fun formatInfoData(animal: Animal): MutableList<ViewItems> {
        val viewItems = mutableListOf<ViewItems>()
        selectedArea?.let { area ->
            viewItems.add(ViewItems.MainView(area))
            val areaAnimals = animal.results.filter { it.a_location.contains(area.e_name) }
            if (areaAnimals.isNotEmpty()) {
                viewItems.add(ViewItems.AnimalTitle("動物資料"))
                areaAnimals.forEach { viewItems.add(ViewItems.AnimalList(it)) }
            }
        }
        return viewItems
    }

    companion object {
        const val TAG = "AreaDetailViewModel"
    }
}