package com.framirez.pokedexapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    private val baseUrl = "https://pokeapi.co/api/v2/"

    val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getPokemonService() : PokemonService {
        return retrofit.create(PokemonService::class.java)
    }

}