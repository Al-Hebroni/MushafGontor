package com.mushaf.gontor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mushaf.gontor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMenuClickListeners()
    }

    private fun setupMenuClickListeners() {
        binding.menuBacaMushaf.setOnClickListener {
            val intent = Intent(this, MushafActivity::class.java)
            startActivity(intent)
        }


        binding.menuBacaPerAyat.setOnClickListener {
            // Memastikan format Intent sudah benar
            val intent = Intent(this, SurahListActivity::class.java)
            startActivity(intent)
        }

        binding.menuBookmark.setOnClickListener {
            showToast("Menu Bookmark diklik!")
        }
        // ... listener lainnya ...
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

