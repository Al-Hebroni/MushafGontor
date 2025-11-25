package com.mushaf.gontor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mushaf.gontor.data.model.Ayat
import com.mushaf.gontor.databinding.ItemAyatBinding

class AyatListAdapter(private val listAyat: List<Ayat>) :
    RecyclerView.Adapter<AyatListAdapter.AyatViewHolder>() {

    // Kelas internal untuk menampung view dari setiap item ayat
    inner class AyatViewHolder(val binding: ItemAyatBinding) : RecyclerView.ViewHolder(binding.root)

    // Fungsi ini dipanggil untuk membuat ViewHolder baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        // Menghubungkan layout item_ayat.xml ke adapter ini
        val binding = ItemAyatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AyatViewHolder(binding)
    }

    // Fungsi ini untuk mengisi data ke dalam setiap baris ayat
    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        // Mengambil data ayat pada posisi tertentu
        val ayat = listAyat[position]

        holder.binding.apply {
            // Memastikan data dari model myquran.com ditampilkan dengan benar
            tvAyatNumber.text = "${ayat.nomorAyat}." // Mengubah Int menjadi String dengan tambahan titik
            tvAyatArabic.text = ayat.teksArab
            tvAyatTranslation.text = ayat.terjemahan
        }
    }

    // Fungsi ini untuk memberitahu RecyclerView berapa total ayat yang akan ditampilkan
    override fun getItemCount(): Int {
        return listAyat.size
    }
}

