package com.ncrdesarrollo.ejerciciosvarios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncrdesarrollo.ejerciciosvarios.R
import com.ncrdesarrollo.ejerciciosvarios.SuperHero

class SuperHeroAdapter(private val superHeroList: List<SuperHero>, private val onClickListener:(SuperHero)->Unit):
    RecyclerView.Adapter<SuperHeroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)//Nota: a los adapter no se le debe pasar el context desde la activity
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))

    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return superHeroList.size
    }
}