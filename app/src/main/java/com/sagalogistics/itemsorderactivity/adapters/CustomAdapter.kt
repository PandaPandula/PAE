package com.sagalogistics.itemsorderactivity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.R
import com.sagalogistics.lib.database.Repository
import com.sagalogistics.lib.models.Item
import com.squareup.picasso.Picasso

class CustomAdapter(private val items: ArrayList<Triple<Item, Int, String>>, private val context: Context): RecyclerView.Adapter<CustomAdapter.ItemHolder>() {


    class ItemHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun render(item: Triple<Item, Int, String>) {
            val itemName = view.findViewById<TextView>(R.id.name)
            val itemMarca = view.findViewById<TextView>(R.id.marca)
            val itemQuantity = view.findViewById<TextView>(R.id.quantity)
            val itemImg = view.findViewById<ImageView>(R.id.img)
            val itemOrder = item.third;

            //Nom de l'item
            itemName.text = item.first.name

            //Marca de l'item
            itemMarca.text = item.first.name.split(" - ").toString()[1].toString()

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

        holder.view.findViewById<TextView>(R.id.minus).setOnClickListener {
            val quant = holder.view.findViewById<TextView>(R.id.quantity).text.toString().toInt().dec()
            //Actualitzem valor a la base de dades
            val a = Repository.getInstance().getOrder(items[position].third).get()!!
            a.updateItem(items[position].first.key!!,quant)

            //Actualitzem el valor en local
            holder.view.findViewById<TextView>(R.id.quantity).text = quant.toString()
            Repository.getInstance().updateOrder(a.key!!, a)
        }
        holder.view.findViewById<TextView>(R.id.plus).setOnClickListener {
            val quant = holder.view.findViewById<TextView>(R.id.quantity).text.toString().toInt().inc()
            //Actualitzem valor a la base de dades
            val a = Repository.getInstance().getOrder(items[position].third).get()!!
            a.updateItem(items[position].first.key!!,quant)

            //Actualitzem el valor en local
            holder.view.findViewById<TextView>(R.id.quantity).text = quant.toString()
            Repository.getInstance().updateOrder(a.key!!, a)
        }



    }

    override fun getItemCount() : Int = items.size

}
