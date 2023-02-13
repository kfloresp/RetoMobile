package com.rgk.retomobile.module.detail_screen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rgk.retomobile.databinding.ItemDessertBinding
import com.rgk.retomobile.databinding.ItemListPeliculaBinding
import com.rgk.retomobile.module.common.basicDiffUtil
import com.rgk.retomobile.module.common.loadUrl
import com.rgk.retomobile.module.detail_screen.model.DetailModel
import com.rgk.retomobile.module.home_screen.data.model.Actor
import com.rgk.retomobile.module.home_screen.data.model.Director
import com.rgk.retomobile.module.home_screen.data.model.PeliculaItem
import java.util.*

class AdpLstItemPelicula () :
    RecyclerView.Adapter<AdpLstItemPelicula.ViewHolder>() {

    var mData: List<DetailModel> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.clave == new.clave }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDessertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                with(mData[position]) {
                    txtName.text = "$clave"
                    txtDesc.text = "$valor"
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(val binding: ItemDessertBinding) :
        RecyclerView.ViewHolder(binding.root)
}