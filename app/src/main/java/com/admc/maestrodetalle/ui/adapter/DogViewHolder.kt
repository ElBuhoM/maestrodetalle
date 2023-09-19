package com.admc.maestrodetalle.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.admc.maestrodetalle.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view:View): ViewHolder(view) {
   private val binding = ItemDogBinding.bind(view)
    fun renderIimage(image:String){
        Picasso.get().load(image).into(binding.ivDog)
    }
}