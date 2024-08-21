package com.example.taipeizoointroduction.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Importdate(
    val date: String,
    val timezone: String,
    val timezone_type: Int
): Parcelable
