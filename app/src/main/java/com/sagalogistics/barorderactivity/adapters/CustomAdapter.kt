package com.sagalogistics.barorderactivity.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.R
import com.sagalogistics.itemsorderactivity.ItemsOrderActivity
import com.sagalogistics.lib.models.Bar
import com.sagalogistics.lib.models.Item
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class CustomAdapter(private val items: List<Bar>, private val context: Context): RecyclerView.Adapter<CustomAdapter.ItemHolder>() {


    class ItemHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun render(item: Bar) {

            val itemName = view.findViewById<TextView>(R.id.name)
            val itemWeight = view.findViewById<TextView>(R.id.weight)
            val itemImg = view.findViewById<ImageView>(R.id.img)

            itemName.text = item.name
            //itemWeight.text = item.weight.toString()

            //item.image?.let { itemImg.loadUrl(it) }

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
        holder.view.setOnClickListener {
            val intent = Intent(context, ItemsOrderActivity::class.java)
            intent.putExtra("key", items[position].key)
            context.startActivity(intent)

        }

    }

    override fun getItemCount() : Int = items.size

}
