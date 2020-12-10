package com.framirez.pokedexapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.framirez.pokedexapp.R
import com.framirez.pokedexapp.models.CharacterResponse
import com.framirez.pokedexapp.viewholders.FavoritePokemonViewHolder

class FavoritePokemonAdapter : RecyclerView.Adapter<FavoritePokemonViewHolder>() {

    var pokemons: List<CharacterResponse> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_fav_view_holder, parent, false)
        return FavoritePokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritePokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount() = pokemons.size


}