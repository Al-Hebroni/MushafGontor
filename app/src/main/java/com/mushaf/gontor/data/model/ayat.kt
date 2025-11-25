package com.mushaf.gontor.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ayat(
    @SerializedName("nomorAyat")
    val nomorAyat: Int,

    @SerializedName("teksArab")
    val teksArab: String,

    @SerializedName("teksIndonesia")
    val terjemahan: String
) : Parcelable

