package com.mushaf.gontor.data.model

import com.google.gson.annotations.SerializedName

// Model ini bertugas untuk "membungkus" respons detail surah
// karena API myquran.com mengirimkannya di dalam objek "data"
data class DetailSurahResponse(
    @SerializedName("data")
    val data: DetailSurahData
)

// Model ini merepresentasikan objek "data" di dalam respons detail,
// yang berisi daftar ayat.
data class DetailSurahData(
    @SerializedName("ayat")
    val listAyat: List<Ayat>
)

