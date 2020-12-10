package com.framirez.pokedexapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.framirez.pokedexapp.PokeListViewModel
import com.framirez.pokedexapp.R
import com.framirez.pokedexapp.adapters.AllPokemonAdapter
import kotlinx.android.synthetic.main.fragment_all_pokemon.*

class AllPokemonFragment : Fragment() {

    private val viewModel: PokeListViewModel by viewModels()
    private val adapter = AllPokemonAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel.getPokemonList()
        return inflater.inflate(R.layout.fragment_all_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcv_all_pokemon.adapter = adapter
        rcv_all_pokemon.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))

        viewModel.getPokemonListResponse().observe(viewLifecycleOwner){characterList ->
            adapter.pokemons = characterList
        }
    }
}