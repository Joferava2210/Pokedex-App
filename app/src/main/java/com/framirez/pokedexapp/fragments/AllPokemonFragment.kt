package com.framirez.pokedexapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.framirez.pokedexapp.R
import com.framirez.pokedexapp.adapters.AllPokemonAdapter
import com.framirez.pokedexapp.models.PokemonResponse
import com.framirez.pokedexapp.viewmodels.CreatePokemonViewModel
import com.framirez.pokedexapp.viewmodels.PokeListViewModel
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_all_pokemon.*
import java.util.concurrent.TimeUnit

class AllPokemonFragment : Fragment() {

    private val viewModel: PokeListViewModel by viewModels()
    private val viewModelCreate : CreatePokemonViewModel by viewModels()
    private val adapter = AllPokemonAdapter()
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel.getPokemonList()
        return inflater.inflate(R.layout.fragment_all_pokemon, container, false)
    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcv_all_pokemon.adapter = adapter
        rcv_all_pokemon.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))

        viewModel.getPokemonListResponse().observe(viewLifecycleOwner){characterList ->
            adapter.pokemons = characterList
            rcv_all_pokemon.visibility = View.VISIBLE
        }

        viewModel.getIsLoading().observe(viewLifecycleOwner) { isLoading ->
            pb_loading.visibility = if(isLoading) View.VISIBLE else View.GONE
        }

        adapter.getOnAddPokemonFavClick().observe(viewLifecycleOwner) { pokemon ->
            if (pokemon != null) {
                viewModelCreate.insertPokemon(pokemon)
            }
        }

        disposables.add(searchEditText.textChanges()
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.filter.filter(it)
                })

        adapter.getOnItemClick().observe(viewLifecycleOwner) { pokemon ->
            //TODO Es necesario pasar el "pokemon" como parámetro, para eso se debe agregar.
          //  if(NavHostFragment.findNavController(this).currentDestination?.id == R.id.allPokemonFragment2) {
                //NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_menuFragment);
                if (pokemon != null) {
                    val navDirections: NavDirections = AllPokemonFragmentDirections.actionAllPokemonFragment2ToPokemonDetails2(pokemon.name)
                    NavHostFragment.findNavController(this).navigate(navDirections)
                }
//            }
        }

    }

    fun Fragment.mayNavigate(): Boolean {

        val navController = findNavController()
        val destinationIdInNavController = navController.currentDestination?.id
        val destinationIdOfThisFragment = view?.getTag(R.id.pokemonDetails2) ?: destinationIdInNavController

        // check that the navigation graph is still in 'this' fragment, if not then the app already navigated:
        if (destinationIdInNavController == destinationIdOfThisFragment) {
            view?.setTag(R.id.pokemonDetails2, destinationIdOfThisFragment)
            return true
        } else {
            Log.d("FragmentExtensions", "May not navigate: current destination is not the current fragment.")
            return false
        }
    }


}