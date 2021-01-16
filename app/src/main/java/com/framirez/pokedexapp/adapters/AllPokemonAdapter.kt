package com.framirez.pokedexapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.framirez.pokedexapp.R
import com.framirez.pokedexapp.database.PokemonEntity
import com.framirez.pokedexapp.helpers.SingleLiveEvent
import com.framirez.pokedexapp.models.CharacterResponse
import com.framirez.pokedexapp.models.PokemonResponse
import kotlinx.android.synthetic.main.pokemon_item_view_holder.view.*
import java.util.*

class AllPokemonAdapter : RecyclerView.Adapter<AllPokemonAdapter.AllPokemonViewHolderInner>(), Filterable {

    private val onAddPokemonFavClick: SingleLiveEvent<PokemonEntity> = SingleLiveEvent()
    private val onItemClick: SingleLiveEvent<PokemonEntity> = SingleLiveEvent()

    var pokemons : List<CharacterResponse> = emptyList()
        set(value) {
            field = value
            filteredCharacters = value
            notifyDataSetChanged()
        }

    private var filteredCharacters: List<CharacterResponse> = emptyList()

    inner class AllPokemonViewHolderInner(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pokemon: CharacterResponse) {
            itemView.tv_poke_name.text = pokemon.name
            val id = pokemon.url.split("/")
            val idFormatter = id[id.size-2]
            //val urlPicture = "https://github.com/PokeAPI/sprites/blob/master/sprites/pokemon/${idFormatter}.png"
            val urlPicture = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${idFormatter}.png"
            Glide.with(itemView.context)
                    .load(urlPicture)
                    .circleCrop()
                    .into(itemView.img_pokemon)

            val isFavorite = if(pokemon.isFavorite) R.drawable.ic_baseline_star_24 else R.drawable.ic_favorite_pokemon
            itemView.img_favorite_pokemon.setImageResource(isFavorite)
//            itemView.setOnClickListener{
//                listener.onNext(pokemon)
//            }
            itemView.img_favorite_pokemon.setOnClickListener{ view ->
//                itemView.img_favorite_pokemon.setImageResource(R.drawable.ic_baseline_star_24)
//                pokemon.isFavorite = true
                if (pokemon.isFavorite == true){
                    itemView.img_favorite_pokemon.setImageResource(R.drawable.ic_favorite_pokemon)
                    pokemon.isFavorite = false

                } else {
                    itemView.img_favorite_pokemon.setImageResource(R.drawable.ic_baseline_star_24)
                    pokemon.isFavorite = true
                    //onAddPokemonFavClick.postValue(PokemonEntity(idFormatter, pokemon.name, "", urlPicture))
                    onAddPokemonFavClick.postValue(PokemonEntity(UUID.randomUUID().toString(), pokemon.name, "", urlPicture))
                }
                //onAddPokemonFavClick.postValue(PokemonEntity(idFormatter, pokemon.name, "", urlPicture)) TODO: Se debe usar este, pero se debe validar que no esté previamente insertado el id
            }

            itemView.lay_all_pokemon.setOnClickListener{ view ->
                onItemClick.postValue(PokemonEntity(idFormatter, pokemon.name, "", urlPicture))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPokemonViewHolderInner {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_view_holder, parent, false)
        return AllPokemonViewHolderInner(view)
    }

    override fun onBindViewHolder(holder: AllPokemonViewHolderInner, position: Int) {
        holder.bind(filteredCharacters[position])
    }

    override fun getItemCount(): Int {
        return filteredCharacters.size
    }

    fun getOnAddPokemonFavClick(): LiveData<PokemonEntity?> {
        return onAddPokemonFavClick
    }


    fun getOnItemClick(): LiveData<PokemonEntity?> {
        return onItemClick
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                filteredCharacters = pokemons
                charSequence?.let {
                    if (charSequence.isNotEmpty()) {
                        filteredCharacters = pokemons.filter { character ->
                            character.name.toLowerCase(
                                    Locale.getDefault()
                            ).contains(charSequence.toString().toLowerCase(Locale.getDefault()))
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredCharacters
                return filterResults
            }

            override fun publishResults(
                    charSequence: CharSequence?,
                    filteredResults: FilterResults?
            ) {
                filteredCharacters = filteredResults?.values as List<CharacterResponse>
                notifyDataSetChanged()
            }
        }
    }

}

