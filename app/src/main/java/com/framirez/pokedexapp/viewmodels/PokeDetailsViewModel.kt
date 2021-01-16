package com.framirez.pokedexapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.framirez.pokedexapp.models.PokemonResponse
import com.framirez.pokedexapp.network.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeDetailsViewModel: ViewModel() {

    private val retrofitProvider = RetrofitProvider()
    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val pokemonResponse: MutableLiveData<PokemonResponse> = MutableLiveData()

    fun getIsLoading(): LiveData<Boolean> {
        return isMakingRequest
    }

    fun getPokemonDetails(): LiveData<PokemonResponse>{
        return pokemonResponse
    }

    fun getPokemonDetails(id: String) {
        isMakingRequest.postValue(true)
        retrofitProvider.getPokemonService().getPokemon(id).enqueue(object : Callback<PokemonResponse> {
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