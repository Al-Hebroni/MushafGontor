package com.mushaf.gontor.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

class MushafActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMushafBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMushafBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            binding.root.setPadding(0, systemBars.top, 0, 0)
            insets
        }

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
        val pdfFileName = "Juz1.pdf"

        try {
            val file = getFileFromAssets(this, pdfFileName)

            binding.pdfView.statusListener = object : PdfRendererView.StatusCallBack {
                override fun onPdfRenderSuccess() {
                    // Mengubah tampilan menjadi Horizontal seperti buku
                    val recyclerView = binding.pdfView.recyclerView
                    recyclerView.layoutManager = LinearLayoutManager(
                        this@MushafActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )

                    // Menambahkan efek Snap (berhenti per halaman)
                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(recyclerView)
                }

                override fun onError(error: Throwable) {
                    Toast.makeText(this@MushafActivity, "Error: ${error.message}", Toast.LENGTH_LONG).show()
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

        if (!file.exists()) {
            context.assets.open(fileName).use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
        return file
    }
}