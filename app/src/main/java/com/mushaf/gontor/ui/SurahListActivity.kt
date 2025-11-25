package com.mushaf.gontor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mushaf.gontor.data.model.Surah
import com.mushaf.gontor.databinding.ActivitySurahListBinding
import com.mushaf.gontor.ui.adapter.SurahListAdapter
import java.io.IOException

class SurahListActivity : AppCompatActivity() {

    // Variabel binding. 'lateinit' berarti kita janji akan mengisinya nanti di onCreate
    private lateinit var binding: ActivitySurahListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. INI SANGAT PENTING: Mengembangkan layout dan menyimpannya ke variabel binding
        binding = ActivitySurahListBinding.inflate(layoutInflater)

        // 2. Menampilkan root view dari binding ke layar
        setContentView(binding.root)

        // Setelah setContentView, baru kita bisa aman mengakses binding.*
        setupToolbar()

        // Panggil fungsi untuk memuat data secara OFFLINE
        loadDataFromAssets()
    }

    private fun setupToolbar() {
        // Pastikan ID di XML adalah 'toolbar', bukan 'toolbar_detail' atau lainnya
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadDataFromAssets() {
        // Akses binding aman di sini karena dipanggil setelah onCreate selesai inisialisasi
        binding.progressBar.visibility = View.VISIBLE
        binding.rvSurahList.visibility = View.GONE

        try {
            // 1. Membaca file data.json dari folder assets
            // Pastikan file 'data.json' ada di folder: src/main/assets/
            val jsonString = assets.open("data.json").bufferedReader().use { it.readText() }

            // 2. Mengubah JSON menjadi List<Surah>
            val listSurahType = object : TypeToken<List<Surah>>() {}.type
            val surahList: List<Surah> = Gson().fromJson(jsonString, listSurahType)

            // 3. Tampilkan di RecyclerView
            val adapter = SurahListAdapter(surahList)
            binding.rvSurahList.layoutManager = LinearLayoutManager(this)
            binding.rvSurahList.adapter = adapter

            // 4. Klik item untuk ke detail
            adapter.setOnItemClickCallback { surah ->
                val intent = Intent(this, SurahDetailActivity::class.java).apply {
                    putExtra(SurahDetailActivity.EXTRA_SURAH, surah)
                }
                startActivity(intent)
            }

            binding.rvSurahList.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE

        } catch (ioException: IOException) {
            ioException.printStackTrace()
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, "Gagal membaca data.json: ${ioException.message}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}