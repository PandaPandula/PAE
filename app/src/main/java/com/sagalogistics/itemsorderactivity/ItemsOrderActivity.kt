package com.sagalogistics.itemsorderactivity

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.R
import com.sagalogistics.itemsorderactivity.adapters.CustomAdapter
import com.sagalogistics.lib.database.FutureHelper
import com.sagalogistics.lib.database.Repository
import com.sagalogistics.lib.models.Item
import com.sagalogistics.lib.models.Order
import com.sagalogistics.utils.dialogs.ItemDialog

class ItemsOrderActivity : AppCompatActivity() {
    lateinit var mRecyclerView : RecyclerView
    private lateinit var globalOrder : Set<Order>;

    private var orders: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_order)

        //Creem la llista d'items
        val items = ArrayList<Triple<Item, Int, String>>()

        orders = Repository.getInstance().getBar(intent.extras?.get("key") as String).get()?.orders!!
        val ordersPrime = FutureHelper.getListOfKeys(orders, Order::class)
        globalOrder = ordersPrime
        ordersPrime.forEach { order ->
            val itemsPrime = FutureHelper.getListOfKeys(order.items.keys, Item::class)
            itemsPrime.forEach { item ->
                val itemTriple = Triple(item, order.items[item.key]!!, order.key!!)
                items.add(itemTriple)
            }
        }

        setUpRecyclerView(items)
    }

    fun add(View: View?){

    }

    fun rest(View: View?){

    }

    fun validate(View: View?) {
        //Actualitzem las orders a back
        val newFragment = ItemDialog(R.string.ItemDialog,R.string.accept,R.string.cancel)
        newFragment.show(supportFragmentManager, "hola")


    }

    fun back (View: View?) {

        val newFragment = ItemDialog(R.string.BackDialog,R.string.accept,R.string.cancel)
        newFragment.show(supportFragmentManager, "hola")
        for (order in globalOrder) {
            Repository.getInstance().updateOrder(order.key!!, order)
        }
    }

    private fun setUpRecyclerView(items: ArrayList<Triple<Item, Int, String>>) {
        mRecyclerView = findViewById(R.id.recylerviewitems)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(items,this)
        mRecyclerView.adapter = adapter
    }
}