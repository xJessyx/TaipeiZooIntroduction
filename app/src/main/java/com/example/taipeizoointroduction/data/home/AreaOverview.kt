package com.example.taipeizoointroduction.data.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AreaOverview(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Results>,
    val sort: String
): Parcelable


