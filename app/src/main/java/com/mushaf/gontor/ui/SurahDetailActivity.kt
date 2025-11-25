package com.mushaf.gontor.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.mushaf.gontor.databinding.ActivityMushafBinding
import com.rajat.pdfviewer.PdfRendererView
import java.io.File
import java.io.FileOutputStream

// Pastikan import ini ada. Jika merah, coba 'Invalidate Caches' seperti saran sebelumnya.
// Jika library afreakyelf menggunakan package asli barteksc:
//import com.github.barteksc.pdfviewer.PDFView
// Jika library afreakyelf menggunakan package berbeda (jarang, tapi mungkin):
// import io.github.afreakyelf.pdfviewer.PDFView

class SurahDetailActivity : AppCompatActivity() {

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
        supportActionBar?.title = "Juz 1" // Set judul toolbar
        binding.toolbarMushaf.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadPdfFromAssets() {
        // Nama file ini harus sama persis dengan yang ada di folder assets
        val pdfFileName = "Juz1.pdf"

        try {
            val file = getFileFromAssets(this, pdfFileName)

            binding.pdfView.statusListener = object : PdfRendererView.StatusCallBack {
                override fun onPdfRenderSuccess() {
                    // Mengubah layout manager menjadi Horizontal
                    val recyclerView = binding.pdfView.recyclerView
                    recyclerView.layoutManager = LinearLayoutManager(
                        this@SurahDetailActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )

                    // Menambahkan efek Snap (agar berhenti pas di halaman, tidak menggantung)
                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(recyclerView)
                }

                override fun onError(error: Throwable) {
                    Toast.makeText(this@SurahDetailActivity, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
            }

            binding.pdfView.initWithFile(file)

        } catch (e: Exception) {
            Toast.makeText(this, "File PDF tidak ditemukan: $pdfFileName", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun getFileFromAssets(context: Context, fileName: String): File {
        val file = File(context.cacheDir, fileName)

        // Cek jika file belum ada di cache, baru copy.
        if (!file.exists()) {
            context.assets.open(fileName).use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
        return file
    }

    companion object {
        const val EXTRA_SURAH = "EXTRA_SURAH"
    }
}