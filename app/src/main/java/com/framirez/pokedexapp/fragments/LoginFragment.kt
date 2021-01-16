package com.framirez.pokedexapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.framirez.pokedexapp.R
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*

class LoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            val sharedPreferences = EncryptedSharedPreferences.create(
                    "FILE",
                    masterKey,
                    context?.applicationContext!!,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            val editor = sharedPreferences.edit()

            editor.putString("NAME", txt_coach.text.toString())
            if (radioButton_m.isChecked){
                editor.putString("GENDER", "M")
            } else {
                editor.putString("GENDER", "F")
            }
            editor.apply()
            if(txt_coach.text?.length!! < 5){
                val builder = AlertDialog.Builder(context)
                builder.setMessage("El nombre debe ser mayor de 5 caracteres")
                        .setPositiveButton("Aceptar",
                                DialogInterface.OnClickListener { dialog, id ->
                                    // FIRE ZE MISSILES!
                                })
                // Create the AlertDialog object and return it
                builder.create()
                builder.show()
            } else
            {
                findNavController().navigate(R.id.action_loginFragment_to_bottomMenuFragment)
            }
        }
    }




}