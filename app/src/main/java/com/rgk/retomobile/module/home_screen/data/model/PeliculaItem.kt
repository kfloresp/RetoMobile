package com.rgk.retomobile.module.home_screen.data.model

import android.os.Parcel
import android.os.Parcelable

data class PeliculaItem(
    val actor: List<Actor>,
    val director: List<Director>,
    val fotoPortada: String,
    val nombrePelicula: String,
    val paisOrigen: PaisOrigen,
    val sinopsis: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Actor)!!,
        parcel.createTypedArrayList(Director)!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(PaisOrigen::class.java.classLoader)!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(actor)
        parcel.writeTypedList(director)
        parcel.writeString(fotoPortada)
        parcel.writeString(nombrePelicula)
        parcel.writeParcelable(paisOrigen, flags)
        parcel.writeString(sinopsis)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PeliculaItem> {
        override fun createFromParcel(parcel: Parcel): PeliculaItem {
            return PeliculaItem(parcel)
        }

        override fun newArray(size: Int): Array<PeliculaItem?> {
            return arrayOfNulls(size)
        }
    }
}