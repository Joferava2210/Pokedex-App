package com.framirez.pokedexapp.network

import com.framirez.pokedexapp.models.CharacterResponse
import com.framirez.pokedexapp.models.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon")
    fun getPokemonList() : Call<DataResponse>
}