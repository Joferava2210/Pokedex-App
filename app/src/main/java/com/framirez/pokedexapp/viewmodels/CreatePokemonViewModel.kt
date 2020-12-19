package com.framirez.pokedexapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.framirez.pokedexapp.database.PokemonDatabase
import com.framirez.pokedexapp.database.PokemonEntity
import com.framirez.pokedexapp.repository.PokemonRepository
import kotlinx.coroutines.launch

class CreatePokemonViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: PokemonRepository
    private val database: PokemonDatabase = PokemonDatabase.getDatabase(application)

    init {
        repository = PokemonRepository(database.pokemonDao())
    }

    fun insertPokemon(pokemonEntity: PokemonEntity) {
        viewModelScope.launch {
            repository.insert(pokemonEntity)
        }
    }
}