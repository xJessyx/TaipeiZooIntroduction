package com.example.taipeizoointroduction.areaHome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeizoointroduction.api.ZooApi
import com.example.taipeizoointroduction.data.home.AreaOverview
import kotlinx.coroutines.launch

class AreaHomeViewModel : ViewModel() {

    private val _areaOverview = MutableLiveData<AreaOverview>()
    val areaOverview: LiveData<AreaOverview>
        get() = _areaOverview

    init {
        getAreaOverview()
    }

    private fun getAreaOverview() {
        viewModelScope.launch {
            try {
                _areaOverview.value = ZooApi.retrofitApiService.getAreaOverview().result
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }

    companion object {
        const val TAG = "AreaHomeViewModel"
    }
}