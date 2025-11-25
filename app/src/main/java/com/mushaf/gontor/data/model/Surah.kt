package com.mushaf.gontor.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Surah(
    @SerializedName("nomor")
    val nomor: String,

    @SerializedName("nama")
    val namaLatin: String,

    @SerializedName("asma")
    val nama: String,

    @SerializedName("type")
    val tempatTurun: String,

    @SerializedName("arti")
    val arti: String,

    // PENTING: Mengambil daftar ayat langsung dari JSON
    @SerializedName("ayat")
    val listAyat: List<Ayat>
) : Parcelable