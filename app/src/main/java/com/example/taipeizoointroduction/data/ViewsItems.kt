package com.example.taipeizoointroduction.data

import com.example.taipeizoointroduction.data.animal.ResultX
import com.example.taipeizoointroduction.data.home.Results


sealed class ViewItems {
    data class MainView(
        val main: Results
    ) : ViewItems()

    data class AnimalList(
        val animalData: ResultX
    ) : ViewItems()

    data class AnimalTitle(
        val animalTitle: String
    ) : ViewItems()
}