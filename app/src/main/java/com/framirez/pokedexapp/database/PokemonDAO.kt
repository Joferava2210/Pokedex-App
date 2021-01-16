package com.framirez.pokedexapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {
    @Insert
    suspend fun insert(pokemon: PokemonEntity)

    @Query("select * from pokemonentity")
    fun getAllPokemon(): Flow<List<PokemonEntity>>

    @Query("DELETE FROM pokemonentity WHERE id = :id")
    suspend fun deletePokemon(id: String)
}