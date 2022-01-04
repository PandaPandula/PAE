package com.sagalogistics.itemsorderactivity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.R
import com.sagalogistics.lib.models.Item
import com.squareup.picasso.Picasso

class CustomAdapter(private val items: ArrayList<Pair<Item, Int>>, private val context: Context): RecyclerView.Adapter<CustomAdapter.ItemHolder>() {


    class ItemHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun render(item: Pair<Item, Int>) {
            val itemName = view.findViewById<TextView>(R.id.name)
            val itemQuantity = view.findViewById<TextView>(R.id.weight)
            val itemImg = view.findViewById<ImageView>(R.id.img)

            //Nom de l'item
            itemName.text = item.first.name

            //Quantitat
            itemQuantity.text = item.second.toString()

            //Imatge de l'item
            item.first.image?.let { itemImg.loadUrl(it) }


        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemHolder(layoutInflater.inflate(R.layout.innerlayout_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.render(items[position])
    }

    override fun getItemCount() : Int = items.size

}
