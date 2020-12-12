package com.framirez.pokedexapp.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.framirez.pokedexapp.R
import com.framirez.pokedexapp.models.CharacterResponse
import io.reactivex.rxjava3.core.Observer
import kotlinx.android.synthetic.main.pokemon_item_view_holder.view.*

class AllPokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: CharacterResponse, listener: Observer<CharacterResponse>) {
        itemView.tv_poke_name.text = pokemon.name
        val id = pokemon.url.split("/")
        Log.d("log_id", id.toString())
        val idFormatter = id[id.size-2]
        Log.d("log_idFormatter", idFormatter)
        //val urlPicture = "https://github.com/PokeAPI/sprites/blob/master/sprites/pokemon/${idFormatter}.png"
        val urlPicture = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${idFormatter}.png"
        Log.d("log_urlPicture", urlPicture)
        Glide.with(itemView.context)
                .load(urlPicture)
                .circleCrop()
                .into(itemView.img_pokemon)

        val isFavorite = if(pokemon.isFavorite) R.drawable.ic_baseline_star_24 else R.drawable.ic_favorite_pokemon
        itemView.img_favorite_pokemon.setImageResource(isFavorite)
        itemView.setOnClickListener{
            listener.onNext(pokemon)
        }
        itemView.img_favorite_pokemon.setOnClickListener{
            itemView.img_favorite_pokemon.setImageResource(R.drawable.ic_baseline_star_24)
            pokemon.isFavorite = true
        }
    }

}