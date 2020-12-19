package com.framirez.pokedexapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "description") val description: String,
        @ColumnInfo(name = "picture") val picture: String)
