package com.framirez.pokedexapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.framirez.pokedexapp.R
import com.framirez.pokedexapp.adapters.FavoritePokemonAdapter
import com.framirez.pokedexapp.viewmodels.FavoriteListViewModel
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    private val viewModel: FavoriteListViewModel by viewModels()
    private val adapter = FavoritePokemonAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcv_favorites_pokemon.adapter = adapter

        viewModel.getAllPokemons().observe(viewLifecycleOwner) { favorite ->
            adapter.pokemons = favorite
        }

    }
}