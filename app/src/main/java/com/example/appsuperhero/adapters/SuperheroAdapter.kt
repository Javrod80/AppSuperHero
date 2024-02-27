package com.example.appsuperhero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperhero.data.Superhero
import com.example.appsuperhero.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroAdapter(private var items: List<Superhero> = listOf(), val onClickListener: (position: Int) -> Unit) :
    RecyclerView.Adapter<SuperheroAdapter.SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val binding = ItemSuperheroBinding.inflate((LayoutInflater.from(parent.context)),parent,false)
        return SuperHeroViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
    }

    fun updateItems(results: List<Superhero>?) {
        items = results!!
        notifyDataSetChanged()
    }


    class SuperHeroViewHolder( val binding: ItemSuperheroBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun render(superhero: Superhero) {

            binding.textViewSuper.text = superhero.name
            Picasso.get().load(superhero.image.url).into(binding.superheroImageView)


        }
    }


}




