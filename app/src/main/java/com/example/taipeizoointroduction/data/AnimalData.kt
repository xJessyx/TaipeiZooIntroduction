package com.example.taipeizoointroduction.data

import android.os.Parcelable
import com.example.taipeizoointroduction.data.animal.Animal
import com.example.taipeizoointroduction.data.home.AreaOverview
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimalData(
    val result: Animal
): Parcelable