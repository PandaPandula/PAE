package com.sagalogistics.barorderactivity.adapters

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.R
import com.sagalogistics.itemsorderactivity.ItemsOrderActivity
import com.sagalogistics.lib.models.Bar
import com.squareup.picasso.Picasso

import android.widget.RelativeLayout

import android.graphics.Color

import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.view.View.GONE
import android.view.View.VISIBLE
import com.sagalogistics.utils.dialogs.ItemDialog
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class CustomAdapter(private val items: List<Bar>, private val context: Context): RecyclerView.Adapter<CustomAdapter.ItemHolder>() {


    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }

    fun showImage() {
        val builder = Dialog(context)
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        builder.setOnDismissListener {
            //nothing;
        }
        val imageView = ImageView(context)
        imageView.loadUrl("https://joomly.net/frontend/web/images/googlemap/map.png")
        builder.addContentView(
            imageView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        builder.show()
    }


    class ItemHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun render(item: Bar) {

            val itemName = view.findViewById<TextView>(R.id.name)
            val itemLoca = view.findViewById<TextView>(R.id.direccio)
            val itemImg = view.findViewById<ImageView>(R.id.img)

            itemName.text = item.name.split(".")[0]
            itemLoca.text = "Sant Pepito 123"

            //Imatge de l'item
            itemImg.loadUrl("http://barlorenzo.com/wp-content/uploads/2019/08/slider01.jpg")

        }
        fun ImageView.loadUrl(url: String) {
            //Picasso.with(context).load(url).into(this)
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
        holder.view.setOnLongClickListener {
            showImage()
            true
        }

        if(items[position].name.split(".")[1].toInt() == 1) holder.view.findViewById<TextView>(R.id.status).visibility = VISIBLE

    }

    override fun getItemCount() : Int = items.size

}
