package com.rgk.retomobile.module.home_screen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rgk.retomobile.databinding.ItemListPeliculaBinding
import com.rgk.retomobile.module.common.basicDiffUtil
import com.rgk.retomobile.module.common.loadUrl
import com.rgk.retomobile.module.home_screen.data.model.Actor
import com.rgk.retomobile.module.home_screen.data.model.Director
import com.rgk.retomobile.module.home_screen.data.model.PeliculaItem
import java.util.*

class AdpLstPelicula (private val listener: (PeliculaItem) -> Unit) :
    RecyclerView.Adapter<AdpLstPelicula.ViewHolder>(),Filterable {

    var mData: List<PeliculaItem> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.nombrePelicula == new.nombrePelicula }
    )

    var mDataFilter:List<PeliculaItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListPeliculaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                with(mData[position]) {
                   tvTitulo.text = "$nombrePelicula"
                    imgPortada.loadUrl("$fotoPortada")
                    cardItem.setOnClickListener {
                        if (position != RecyclerView.NO_POSITION) {
                            listener(mData[position])
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(val binding: ItemListPeliculaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(chars: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if  (chars == null || chars.length < 0){
                    filterResults.count = mDataFilter.size
                    filterResults.values = mDataFilter
                }else{
                    var search = chars.toString().lowercase(Locale.ROOT)
                    val itemModal : MutableList<PeliculaItem> = arrayListOf()
                    for (item in mDataFilter){
                        if (item.nombrePelicula!!.lowercase(Locale.ROOT).contains(search)|| buscarDirector(item.director,search) || buscarActor(item.actor,search) ){
                            itemModal.add(item)
                        }
                    }
                    filterResults.count = itemModal.size
                    filterResults.values  = itemModal
                }
                return filterResults
            }

            override fun publishResults(chars: CharSequence?, filterResults: FilterResults?) {
                mData = filterResults!!.values as List<PeliculaItem>
                notifyDataSetChanged()
            }

        }

    }

    private fun buscarActor(actor: List<Actor>, search: String): Boolean {
        actor.forEach {
            if (it.nombre!!.lowercase(Locale.ROOT).contains(search)){
                return true
            }
        }
        return false
    }

    private fun buscarDirector(director: List<Director>, search: String): Boolean {
        director.forEach {
            if (it.nombre!!.lowercase(Locale.ROOT).contains(search)){
                return true
            }
        }
        return false
    }
}