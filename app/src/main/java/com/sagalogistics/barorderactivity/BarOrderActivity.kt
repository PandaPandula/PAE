package com.sagalogistics

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.barorderactivity.adapters.CustomAdapter
import com.sagalogistics.lib.database.FutureHelper
import com.sagalogistics.lib.database.Repository
import com.sagalogistics.lib.database.WeightCalculator
import com.sagalogistics.lib.models.Bar


class BarOrderActivity : Activity()
{
    lateinit var mRecyclerView : RecyclerView
    private lateinit var pesT : TextView
    private lateinit var pesR : EditText

    private var barsList: List<Bar> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_order2)
        pesR = findViewById(R.id.PesR)
        pesT = findViewById(R.id.PesT)
        setUpRecyclerView()
    }

    fun setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.recylerview)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        //Agafem l'usuari
        val user = Repository.getInstance().getUser("-Mq04Y51CY4YFjo7AiqA").get()!!

        //Calculem el weight
        val weightT = WeightCalculator.weightOfUserOrders(user)
        val medianweight = (weightT.first + weightT.second / 2).toInt()
        pesT.text = medianweight.toString()

        //Agafem els bars de l'usuari
        val barlist = user.bars.map{
            Repository.getInstance().getBar(it).get()!!
        }
        barsList = barlist

        val adapter = CustomAdapter(barsList,this)
        mRecyclerView.adapter = adapter
    }
}



