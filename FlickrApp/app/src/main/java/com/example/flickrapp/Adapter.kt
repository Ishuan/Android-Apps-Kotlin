package com.example.flickrapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter(private val itemList: ArrayList<FlickerData>) :
    RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flicker_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Picasso.get().load(itemList[position].media.m).into(holder.itemImage)
        holder.itemAuthor.text = itemList[position].author.split("\"")[1]
        holder.itemTitle.text = itemList[position].title
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage = view.findViewById<ImageView>(R.id.imageView)
        val itemAuthor = view.findViewById<TextView>(R.id.imageAuthor)
        val itemTitle = view.findViewById<TextView>(R.id.imageTitle)
    }

}