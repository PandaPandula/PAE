package com.sagalogistics.itemsorderactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.R
import com.sagalogistics.barorderactivity.adapters.CustomAdapter
import com.sagalogistics.lib.database.Repository
import com.sagalogistics.lib.models.Bar

class ItemsOrderActivity : AppCompatActivity() {
    lateinit var mRecyclerView : RecyclerView

    private var itemList: List<Bar> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_order)
        intent.extras?.get("key")
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recylerviewitems)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val barlist = Repository.getInstance().getUser("-Mq04Y51CY4YFjo7AiqA").get()!!.bars.map{
            Repository.getInstance().getBar(it).get()!!
        }
        itemList = barlist

        val adapter = CustomAdapter(itemList,this)
        mRecyclerView.adapter = adapter
    }
}