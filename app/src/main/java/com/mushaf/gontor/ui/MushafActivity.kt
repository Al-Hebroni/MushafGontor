package com.mushaf.gontor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mushaf.gontor.databinding.ActivityMushafBinding
// Pastikan import ini ada. Jika merah, coba 'Invalidate Caches' seperti saran sebelumnya.
// Jika library afreakyelf menggunakan package asli barteksc:
//import com.github.barteksc.pdfviewer.PDFView
// Jika library afreakyelf menggunakan package berbeda (jarang, tapi mungkin):
// import io.github.afreakyelf.pdfviewer.PDFView

class MushafActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMushafBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMushafBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        loadPdfFromAssets()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarMushaf)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Juz1" // Set judul toolbar
        binding.toolbarMushaf.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadPdfFromAssets() {
        // Nama file ini harus sama persis dengan yang ada di folder assets
        val pdfFileName = "juz1.pdf"

        try {
            // Akses PDFView dari binding
            binding.pdfView.fromAsset(pdfFileName)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .pageSnap(true)
                .autoSpacing(true) // Tambahan: kadang bagus untuk tampilan
                .pageFling(true) // Tambahan: efek fling seperti buku
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // Bagus untuk teks
                .spacing(0)
                .onError { t ->
                    // Menangani error load PDF
                    Toast.makeText(this, "Error memuat PDF: ${t.message}", Toast.LENGTH_LONG).show()
                }
                .onPageError { page, t ->
                    // Menangani error halaman spesifik
                    Toast.makeText(this, "Error di halaman $page: ${t.message}", Toast.LENGTH_LONG).show()
                }
                .load()

        } catch (e: Exception) {
            Toast.makeText(this, "File PDF tidak ditemukan: $pdfFileName", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}