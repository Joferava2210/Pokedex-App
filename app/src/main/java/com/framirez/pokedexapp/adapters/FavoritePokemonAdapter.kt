package com.framirez.pokedexapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.framirez.pokedexapp.R
import com.framirez.pokedexapp.database.PokemonEntity
import kotlinx.android.synthetic.main.pokemon_item_fav_view_holder.view.*

class FavoritePokemonAdapter : RecyclerView.Adapter<FavoritePokemonAdapter.FavoriteViewHolderInner>() {

    var pokemons: List<PokemonEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class FavoriteViewHolderInner(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: PokemonEntity) {
            itemView.tv_poke_name_fav.text = pokemon.name
            Glide.with(itemView.context)
                    .load(pokemon.picture)
                    .circleCrop()
                    .into(itemView.img_pokemon_fav)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolderInner {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_fav_view_holder, parent, false)
        return FavoriteViewHolderInner(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolderInner, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount() = pokemons.size


}