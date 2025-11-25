package com.mushaf.gontor.data.model

import com.google.gson.annotations.SerializedName

// Disesuaikan dengan API myquran.com
data class Ayat(
    @SerializedName("nomorAyat")
    val nomorAyat: Int,

    @SerializedName("teksArab")
    val teksArab: String,

    @SerializedName("teksIndonesia")
    val terjemahan: String
)

