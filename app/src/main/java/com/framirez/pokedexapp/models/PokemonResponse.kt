package com.framirez.pokedexapp.models

import android.os.Parcel
import android.os.Parcelable

data class PokemonResponse(var id: Int, var name: String, var weight: Float, var height: Float, var abilities: List<Ability>, var forms: List<FormResponse>, var moves: List<MoveResponse>, var stats: List<DataStatResponse>): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readFloat(),
            parcel.readFloat(),
            TODO("abilities"),
            TODO("forms"),
            TODO("moves"),
            TODO("stats")) {
    }



    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<PokemonResponse> {
        override fun createFromParcel(parcel: Parcel): PokemonResponse {
            return PokemonResponse(parcel)
        }

        override fun newArray(size: Int): Array<PokemonResponse?> {
            return arrayOfNulls(size)
        }
    }
}
