package com.example.taipeizoointroduction.data.home

import android.os.Parcelable
import com.example.taipeizoointroduction.data.Importdate
import kotlinx.parcelize.Parcelize

@Parcelize
data class Results(
    val _id: Int,
    val _importdate: Importdate,
    val e_category: String,
    val e_geo: String,
    val e_info: String,
    val e_memo: String,
    val e_name: String,
    val e_no: String,
    val e_pic_url: String,
    val e_url: String
): Parcelable