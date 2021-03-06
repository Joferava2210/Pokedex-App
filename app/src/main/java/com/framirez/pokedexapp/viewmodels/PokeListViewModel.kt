package com.framirez.pokedexapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.framirez.pokedexapp.models.CharacterResponse
import com.framirez.pokedexapp.models.DataResponse
import com.framirez.pokedexapp.models.PokemonResponse
import com.framirez.pokedexapp.network.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeListViewModel : ViewModel() {
    private val retrofitProvider = RetrofitProvider()

    private val pokemonListResponse : MutableLiveData<List<CharacterResponse>> = MutableLiveData()
    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val pokemonResponse: MutableLiveData<PokemonResponse> = MutableLiveData()

    fun getPokemonListResponse() : LiveData<List<CharacterResponse>> {
        return pokemonListResponse
    }

    fun getIsLoading(): LiveData<Boolean> {
        return isMakingRequest
    }

    fun getPokemonDetails(): LiveData<PokemonResponse>{
        return pokemonResponse
    }

    fun getPokemonList() {
        isMakingRequest.postValue(true)
        retrofitProvider.getPokemonService().getPokemonList().enqueue(object : Callback<DataResponse>{
            override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
            ) {
                isMakingRequest.postValue(false)
                if (response.isSuccessful){
                    response.body()?.let { unwrappedresponse ->
                        Log.d("Prueba", response.body().toString())
                        pokemonListResponse.postValue(unwrappedresponse.results)
                    }
                } else{
                    isMakingRequest.postValue(false)
                    Log.d("Prueba", response.body().toString())
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                isMakingRequest.postValue(false)
                Log.d("Prueba", t.toString())
            }
        })
    }

    fun getPokemonDetails(id: String) {
        isMakingRequest.postValue(true)
        retrofitProvider.getPokemonService().getPokemon(id).enqueue(object : Callback<PokemonResponse>{
            override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>) {
                isMakingRequest.postValue(false)
                if (response.isSuccessful) {
                    response.body()?.let { unwrappedresponse ->
                        Log.d("Prueba", response.body().toString())
                        pokemonResponse.postValue(unwrappedresponse)
                    }
                } else {
                    isMakingRequest.postValue(false)
                    Log.d("Prueba", response.body().toString())
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                isMakingRequest.postValue(false)
                Log.d("Prueba", t.toString())
            }
        })
    }
}