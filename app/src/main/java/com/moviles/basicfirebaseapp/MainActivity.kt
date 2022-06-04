package com.moviles.basicfirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.moviles.basicfirebaseapp.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    private lateinit var  queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue = Volley.newRequestQueue(this)
        val database = Firebase.database
        val myRef = database.reference

        binding.btn1.setOnClickListener{
            val titulo = binding.etTittle.text.toString()
            val fecha = binding.etYear.text.toString()
            val id = binding.etId.text.toString()
            val formato = binding.etType.text.toString()
            val poster = binding.etPoster.text.toString()
            val genero = binding.etGenre.text.toString()


            myRef.child("pelicula").child("${id}").setValue(Movie("${titulo}","${fecha}","${id}","${formato}"
            ,"${poster}","${genero}"))
            getMoviesList()
        }
       // myRef.child("ejemplo").child("1").setValue(Caard("1","rojo","corazones"))
       // myRef.child("ejemplo").child("2").setValue(Caard("2","negro","corazones"))
       // myRef.child("ejemplo").child("3").setValue(Caard("3","rojo","corazones"))

}
    fun getMoviesList(){
        val url = "https://moviles-2022-fireb-default-rtdb.firebaseio.com/"

        val jsonRequest= JsonObjectRequest( url, Response.Listener<JSONObject> { response ->
            binding.rwMovieLista.adapter = MainAdapter(response.getJSONArray("pelicula"))
        },
            Response.ErrorListener { error ->
                Log.w("JSONRESPONSE",error.message as String)
            })

        queue.add(jsonRequest)

    }
    override fun onStop(){
        super.onStop()
        queue.cancelAll("stopped")
    }

}