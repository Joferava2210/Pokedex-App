package com.framirez.pokedexapp.models

data class DataResponse(var count: Int, var next: String, var previous: String, var results: List<CharacterResponse>)
