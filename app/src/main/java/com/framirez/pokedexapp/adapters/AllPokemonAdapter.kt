package com.framirez.pokedexapp.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.framirez.pokedexapp.R

class AllPokemonAdapter(context: Context): BaseAdapter() {

    private val activity: Activity? = null
    private var inflater: LayoutInflater

    init {
        this.inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return 0;
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        if (inflater == null) {
            if (activity != null) {
                this.inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }
        }

        val view: View?
        if (convertView == null) {
            view = this.inflater.inflate(R.layout.adapter_all_pokemon, parent, false)
        } else {
            view = convertView
        }
        return view
    }

}

