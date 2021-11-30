package com.sagalogistics.barorderactivity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.R
import com.sagalogistics.backend.api.models.Item
import com.squareup.picasso.Picasso

class CustomAdapter (private val items: List<Item>): RecyclerView.Adapter<CustomAdapter.ItemHolder>() {

    lateinit var context: Context
    class ItemHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun render(item: Item) {

            val itemName = view.findViewById<TextView>(R.id.name)
            val itemWeight = view.findViewById<TextView>(R.id.weight)
            val itemImg = view.findViewById<ImageView>(R.id.img)

            itemName.text = item.name
            itemWeight.text = item.weight.toString()

            item.image?.let { itemImg.loadUrl(it) }



        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemHolder(layoutInflater.inflate(R.layout.innerlayout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        
        holder.render(items[position])
    }

    override fun getItemCount() : Int = items.size

}
