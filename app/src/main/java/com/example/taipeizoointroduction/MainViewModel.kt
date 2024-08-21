package com.example.taipeizoointroduction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var toolbarTitle: MutableLiveData<String> = MutableLiveData()
}