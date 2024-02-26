package com.example.appsuperhero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperhero.data.SuperHero
import com.example.appsuperhero.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroAdapter(private var items: List<SuperHero> = listOf()) : RecyclerView.Adapter<SuperHeroViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SuperHeroViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.render(items[position])
    }

    fun updateItems ( results : List<SuperHero>?) {
        items = results!!
        notifyDataSetChanged()
    }

}

class SuperHeroViewHolder (val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root) {


    fun render (superHero:SuperHero){
        binding.nameTextView.text = superHero.name
       // Picasso.get().load (superHero.image.url).into (binding.photoImageView)




    }


}