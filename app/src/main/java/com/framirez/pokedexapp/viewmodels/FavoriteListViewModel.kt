package com.framirez.pokedexapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.framirez.pokedexapp.database.PokemonDatabase
import com.framirez.pokedexapp.database.PokemonEntity
import com.framirez.pokedexapp.repository.PokemonRepository

class FavoriteListViewModel(application: Application) :AndroidViewModel(application) {

    private val repository: PokemonRepository
    private val database: PokemonDatabase = PokemonDatabase.getDatabase(application)

    init {
        repository = PokemonRepository(database.pokemonDao())
    }

    fun getAllPokemons() :LiveData<List<PokemonEntity>> = repository.allPokemon.asLiveData()

}