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
        imageView.loadUrl("https://pbs.twimg.com/profile_images/421503396319735808/BMzE_v6R_400x400.jpeg")
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
            val itemLoca = view.findViewById<TextView>(com.sagalogistics.R.id.direccio)
            val itemImg = view.findViewById<ImageView>(com.sagalogistics.R.id.img)

            itemName.text = item.name
            itemLoca.text = "Sant Pepito 123"

            //item.image?.let { itemImg.loadUrl(it) }

        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemHolder(layoutInflater.inflate(com.sagalogistics.R.layout.innerlayout, parent, false))
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


    }

    override fun getItemCount() : Int = items.size

}
