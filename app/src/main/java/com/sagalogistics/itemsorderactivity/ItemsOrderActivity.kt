package com.sagalogistics.itemsorderactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.R
import com.sagalogistics.itemsorderactivity.adapters.CustomAdapter
import com.sagalogistics.lib.database.Repository
import com.sagalogistics.lib.models.Item

class ItemsOrderActivity : AppCompatActivity() {
    lateinit var mRecyclerView : RecyclerView

    private var itemList: MutableList<Item> = mutableListOf()
    private var orders: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_order)
        orders = Repository.getInstance().getBar(intent.extras?.get("key") as String).get()?.orders!!
        orders.map{
            Repository.getInstance().getOrder(it).get()?.let { it1 ->
                it1.items.map { it2 ->
                    Repository.getInstance().getItem(it2.key).get()?.let { it3 ->
                        itemList.add(it3)
                    }
                }
            }
        }
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recylerviewitems)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(itemList,this)
        mRecyclerView.adapter = adapter
    }
}