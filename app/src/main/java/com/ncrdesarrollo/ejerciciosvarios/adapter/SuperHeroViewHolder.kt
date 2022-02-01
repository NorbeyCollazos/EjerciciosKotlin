package com.ncrdesarrollo.ejerciciosvarios.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ncrdesarrollo.ejerciciosvarios.R
import com.ncrdesarrollo.ejerciciosvarios.SuperHero
import com.ncrdesarrollo.ejerciciosvarios.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view:View): RecyclerView.ViewHolder(view) {

    val binding = ItemSuperheroBinding.bind(view)

    fun render(superHerModel: SuperHero,  onClickListener:(SuperHero)->Unit){
        binding.tvSuperHero.text = superHerModel.superHero
        binding.tvRealName.text = superHerModel.realName
        binding.tvPublisher.text = superHerModel.publisher
        Glide.with(binding.imgPhoto.context).load(superHerModel.photo).into(binding.imgPhoto)

        //para hacer clic en la imagen
        binding.imgPhoto.setOnClickListener {
            Toast.makeText(binding.imgPhoto.context, superHerModel.realName, Toast.LENGTH_LONG).show()
        }

        //para hacer clic en toda la vista
        itemView.setOnClickListener {
            //Toast.makeText(binding.imgPhoto.context, superHerModel.superHero, Toast.LENGTH_LONG).show()
            onClickListener(superHerModel)
        }
    }
}