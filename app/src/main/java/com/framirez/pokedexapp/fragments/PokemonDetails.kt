package com.framirez.pokedexapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.framirez.pokedexapp.R
import com.framirez.pokedexapp.viewmodels.PokeDetailsViewModel
import com.framirez.pokedexapp.viewmodels.PokeListViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import kotlinx.android.synthetic.main.pokemon_item_view_holder.view.*

class PokemonDetails : Fragment() {
    private val viewModel: PokeListViewModel by viewModels()
    private val args: PokemonDetailsArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val name: String = PokemonDetailsArgs.fromBundle(arguments).getHeroInfo()
//        pokeDetailsViewModel = ViewModelProvider(this).get(HeroDetailsViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.getPokemonDetails(args.pokemonName)

        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPokemonDetails().observe(viewLifecycleOwner) { pokemon ->
//            Log.d("Prueba", pokemon.toString())
            val urlPicture = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ pokemon.id+".png"
            Glide.with(view.context)
                    .load(urlPicture)
                    .circleCrop()
                    .into(img_detail)
            txt_nom_pokemon.text = pokemon.name
            txt_altura.text = pokemon.height.toString()
            txt_peso.text = pokemon.weight.toString()
//            txt_hp.text = pokemon.stats[0].base_stat.toString()
//            txt_attack.text = pokemon.stats[1].base_stat.toString()
//            txt_defense.text = pokemon.stats[2].base_stat.toString()
//            txt_special_attack.text = pokemon.stats[3].base_stat.toString()
//            txt_special_defense.text = pokemon.stats[4].base_stat.toString()
//            txt_speed.text = pokemon.stats[5].base_stat.toString()
            seek_hp.progress = pokemon.stats[0].base_stat
            seek_attack.progress = pokemon.stats[1].base_stat
            seek_defense.progress = pokemon.stats[2].base_stat
            seek_special_attack.progress = pokemon.stats[3].base_stat
            seek_special_defense.progress = pokemon.stats[4].base_stat
            seek_speed.progress = pokemon.stats[5].base_stat
            porc_hp.text = pokemon.stats[0].base_stat.toString()+"%"
            porc_attack.text = pokemon.stats[1].base_stat.toString()+"%"
            porc_defense.text = pokemon.stats[2].base_stat.toString()+"%"
            porc_special_attack.text = pokemon.stats[3].base_stat.toString()+"%"
            porc_special_defense.text = pokemon.stats[4].base_stat.toString()+"%"
            porc_speed.text = pokemon.stats[5].base_stat.toString()+"%"

        }
    }

}