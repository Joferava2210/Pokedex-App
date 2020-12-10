package com.framirez.pokedexapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.framirez.pokedexapp.models.CharacterResponse
import com.framirez.pokedexapp.models.DataResponse
import com.framirez.pokedexapp.network.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeListViewModel : ViewModel() {
    private val retrofitProvider = RetrofitProvider()

    private val pokemonListResponse : MutableLiveData<List<CharacterResponse>> = MutableLiveData()

    fun getPokemonListResponse() : LiveData<List<CharacterResponse>> {
        return pokemonListResponse
    }

    fun getPokemonList() {
        retrofitProvider.getPokemonService().getPokemonList().enqueue(object : Callback<DataResponse>{
            override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { unwrappedresponse ->
                        Log.d("Prueba", response.body().toString())
                    }
                } else{
                    Log.d("Prueba", response.body().toString())
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.d("Prueba", t.toString())
            }
        })
    }
}