package com.example.dogimageapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DogImageAdapter(private val dogImages: List<String>) : RecyclerView.Adapter<DogImageAdapter.DogImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog_image, parent, false)
        return DogImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        val imageUrl = dogImages[position]
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.dogImageView)
    }

    override fun getItemCount(): Int {
        return dogImages.size
    }

    class DogImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dogImageView: ImageView = itemView.findViewById(R.id.dogImageView)
    }
}
