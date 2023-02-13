package com.rgk.retomobile.module.home_screen.data.model

import android.os.Parcel
import android.os.Parcelable

data class PaisOrigen(
    val latitud: String,
    val longitud: String,
    val pais: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(latitud)
        parcel.writeString(longitud)
        parcel.writeString(pais)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PaisOrigen> {
        override fun createFromParcel(parcel: Parcel): PaisOrigen {
            return PaisOrigen(parcel)
        }

        override fun newArray(size: Int): Array<PaisOrigen?> {
            return arrayOfNulls(size)
        }
    }
}