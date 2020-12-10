package com.framirez.pokedexapp.models

data class PokemonResponse(var id: Int, var name: String, var weight: Float, var height: Float, var abilities: List<AbilityResponse>, var forms: List<FormResponse>, var moves: List<MoveResponse>, var stats: List<DataStatResponse>)
