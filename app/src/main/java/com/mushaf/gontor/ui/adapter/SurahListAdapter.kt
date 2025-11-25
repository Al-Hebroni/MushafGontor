package com.mushaf.gontor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mushaf.gontor.data.model.Surah
import com.mushaf.gontor.databinding.ItemSurahBinding

class SurahListAdapter(private val listSurah: List<Surah>) :
    RecyclerView.Adapter<SurahListAdapter.SurahViewHolder>() {

    private var onItemClickCallback: ((Surah) -> Unit)? = null

    fun setOnItemClickCallback(callback: (Surah) -> Unit) {
        this.onItemClickCallback = callback
    }

    inner class SurahViewHolder(val binding: ItemSurahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val binding = ItemSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurahViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = listSurah[position]

        holder.binding.apply {
            // Memastikan data dari model myquran.com ditampilkan dengan benar
            tvSurahNumber.text = surah.nomor.toString() // Mengubah Int menjadi String
            tvSurahNameLatin.text = surah.namaLatin
            tvSurahInfo.text = "${surah.tempatTurun.replaceFirstChar { it.uppercase() }} | 0 Ayat"
            tvSurahNameArabic.text = surah.nama
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback?.invoke(surah)
        }
    }

    override fun getItemCount(): Int {
        return listSurah.size
    }
}

