package com.mushaf.gontor.data.model

import com.google.gson.annotations.SerializedName

// Model ini bertugas untuk "membungkus" daftar surah
// karena API myquran.com mengirimkannya di dalam objek "data"
data class SurahResponse(
    @SerializedName("data")
    val data: List<Surah>
)

