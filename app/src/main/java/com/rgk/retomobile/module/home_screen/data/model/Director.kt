package com.rgk.retomobile.module.home_screen.data.model

import android.os.Parcel
import android.os.Parcelable

data class Director(
    val nombre: String,
    val rol: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(rol)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Director> {
        override fun createFromParcel(parcel: Parcel): Director {
            return Director(parcel)
        }

        override fun newArray(size: Int): Array<Director?> {
            return arrayOfNulls(size)
        }
    }
}