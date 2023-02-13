package com.rgk.retomobile.module.map_screen.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.rgk.retomobile.databinding.ActivityMapScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapScreen : AppCompatActivity() {

    companion object {
        const val CLAVE_LATITUD = "MapScreen:latitud"
        const val CLAVE_LONGITUD = "MapScreen:longitud"
        const val CLAVE_PAIS = "MapScreen:pais"
    }

    private lateinit var binding : ActivityMapScreenBinding
    private val viewModel: MapScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Pais origen: ${viewModel.pais}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cargarMap(viewModel.latitud,viewModel.longitud)
    }

    private fun cargarMap(latitud: String, longitud: String) {
        val url = (
            "https://www.google.com/maps?q=" + latitud
                    + "," + longitud
                    + "&hl=es-PY&gl=py&shorturl=1"
        )
        binding.webView.clearCache(false)
        binding.webView.settings?.javaScriptEnabled=true
        binding.webView.loadUrl(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}