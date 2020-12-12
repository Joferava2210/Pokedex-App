package com.framirez.pokedexapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.framirez.pokedexapp.R
import com.framirez.pokedexapp.models.CharacterResponse
import com.framirez.pokedexapp.viewholders.AllPokemonViewHolder
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class AllPokemonAdapter : RecyclerView.Adapter<AllPokemonViewHolder>() {

    private val clickListener: PublishSubject<CharacterResponse> = PublishSubject.create()

    val onCharacterClicked: Observable<CharacterResponse> = clickListener.hide()

    var pokemons : List<CharacterResponse> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_view_holder, parent, false)
        return AllPokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllPokemonViewHolder, position: Int) {
        holder.bind(pokemons[position], clickListener)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }


}

