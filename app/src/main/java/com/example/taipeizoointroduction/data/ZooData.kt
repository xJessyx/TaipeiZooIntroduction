package com.example.taipeizoointroduction.data

import android.os.Parcelable
import com.example.taipeizoointroduction.data.home.AreaOverview
import kotlinx.parcelize.Parcelize

@Parcelize
data class ZooData(
    val result: AreaOverview
): Parcelable

