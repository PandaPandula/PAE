package com.sagalogistics

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.barorderactivity.adapters.CustomAdapter
import com.sagalogistics.lib.database.FutureHelper
import com.sagalogistics.lib.database.Repository
import com.sagalogistics.lib.models.Bar


class BarOrderActivity : Activity()
{
    lateinit var mRecyclerView : RecyclerView

    private var barsList: List<Bar> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_order2)
        setUpRecyclerView()
    }

    fun setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.recylerview)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val barlist = Repository.getInstance().getUser("-Mq04Y51CY4YFjo7AiqA").get()!!.bars.map{
            Repository.getInstance().getBar(it).get()!!
        }
        barsList = barlist

        val adapter = CustomAdapter(barsList,this)
        mRecyclerView.adapter = adapter
    }
}



