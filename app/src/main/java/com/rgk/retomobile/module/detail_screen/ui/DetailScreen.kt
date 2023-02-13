package com.rgk.retomobile.module.detail_screen.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgk.retomobile.R
import com.rgk.retomobile.databinding.ActivityDetailScreenBinding
import com.rgk.retomobile.module.common.intentFor
import com.rgk.retomobile.module.common.loadUrl
import com.rgk.retomobile.module.map_screen.ui.MapScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class DetailScreen : AppCompatActivity() {

    companion object {
        const val CLAVE_ITEM = "HomeScreen:clave"
    }

    private val viewModel: DetailScreenViewModel by viewModels()
    private lateinit var binding: ActivityDetailScreenBinding
    private lateinit var adapter : AdpLstItemPelicula

    private var collapsedMenu: Menu? = null
    private var appBarExpanded = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.animToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "${viewModel.peliculaItem.nombrePelicula}"

        adapter = AdpLstItemPelicula()
        binding.scrollableview.adapter = adapter

        val bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.header
        )

        Palette.from(bitmap).generate { palette ->
            val vibrantColor: Int = palette!!.getVibrantColor(R.color.purple_500!!)
            binding.collapsingToolbar.setContentScrimColor(vibrantColor)
            binding.collapsingToolbar.setStatusBarScrimColor(R.color.black!!)
        }

        binding.header.loadUrl("${viewModel.peliculaItem.fotoPortada}")

        binding.scrollableview.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(this)
        binding.scrollableview.layoutManager = linearLayoutManager

        binding.appbar.addOnOffsetChangedListener { _, verticalOffset ->
            //  Vertical offset == 0 indicates appBar is fully expanded.
            if (abs(verticalOffset) > 200) {
                appBarExpanded = false
                invalidateOptionsMenu()
            } else {
                appBarExpanded = true
                invalidateOptionsMenu()
            }
        }
        binding.floatingActionButton.setOnClickListener{
           goToPais()
        }

        viewModel.detailModelList.observe(this){
            adapter.mData = it
            adapter.notifyDataSetChanged()
        }
    }

    private fun goToPais() {
        startActivity(intentFor<MapScreen> {
            putExtra(MapScreen.CLAVE_LATITUD, viewModel.peliculaItem.paisOrigen.latitud)
            putExtra(MapScreen.CLAVE_LONGITUD, viewModel.peliculaItem.paisOrigen.longitud)
            putExtra(MapScreen.CLAVE_PAIS, viewModel.peliculaItem.paisOrigen.pais)
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (collapsedMenu != null
            && (!appBarExpanded || collapsedMenu!!.size() != 1)
        ) {
            //collapsed
            collapsedMenu!!.add("GoMap")
                .setIcon(R.drawable.ic_baseline_map_24)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        } else {
            //expanded
        }
        return super.onPrepareOptionsMenu(collapsedMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mnu_detalle, menu)
        collapsedMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        if (item.title === "GoMap") {
           goToPais()
        }
        return super.onOptionsItemSelected(item)
    }
}