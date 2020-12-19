package com.framirez.pokedexapp.repository

import com.framirez.pokedexapp.database.PokemonDAO
import com.framirez.pokedexapp.database.PokemonEntity
import kotlinx.coroutines.flow.Flow

class PokemonRepository (private val pokemonDAO: PokemonDAO) {

    suspend fun insert(pokemon: PokemonEntity) {
        pokemonDAO.insert(pokemon)
    }

    val allPokemon : Flow<List<PokemonEntity>> = pokemonDAO.getAllPokemon()
}