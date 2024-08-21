package com.example.taipeizoointroduction.data.animal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultX>,
    val sort: String): Parcelable



