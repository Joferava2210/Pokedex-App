package com.framirez.pokedexapp.network

import com.framirez.pokedexapp.models.DataResponse
import com.framirez.pokedexapp.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    fun getPokemonList() : Call<DataResponse>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: String) : Call<PokemonResponse>


}