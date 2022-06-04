package com.moviles.basicfirebaseapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moviles.basicfirebaseapp.databinding.ItemMovieBinding
import org.json.JSONArray
import org.json.JSONObject

class MainAdapter(private val peli: JSONArray): RecyclerView.Adapter<MainAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainHolder {
        val binding= ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainHolder, position: Int) {
        holder.render(peli[position] as JSONObject)
    }

    override fun getItemCount(): Int = peli.length()



    class MainHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun render (peli: JSONObject){
            binding.genero.setText(peli.getString("genre"))
            binding.aidi.setText(peli.getString("id"))
            binding.poster .setText(peli.getString("poster"))
            binding.titulo .setText(peli.getString("tittle"))
            binding.tipo.setText(peli.getString("type"))
            binding.fecha.setText(peli.getString("year"))
        }
    }
}