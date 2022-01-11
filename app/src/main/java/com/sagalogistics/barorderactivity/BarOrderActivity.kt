package com.sagalogistics

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.barorderactivity.adapters.CustomAdapter
import com.sagalogistics.lib.database.FutureHelper
import com.sagalogistics.lib.database.Repository
import com.sagalogistics.lib.database.WeightCalculator
import com.sagalogistics.lib.models.Bar
import com.sagalogistics.utils.dialogs.OrdersDialog
import android.content.DialogInterface





class BarOrderActivity : AppCompatActivity()
{
    lateinit var mRecyclerView : RecyclerView
    private lateinit var pesT : TextView
    private lateinit var pesR : TextView
    private lateinit var valButton : Button
    private lateinit var weightRange : Pair<Float, Float>

    private var barsList: List<Bar> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_order2)
        pesR = findViewById(R.id.PesR)
        pesT = findViewById(R.id.PesT)
        valButton = findViewById(R.id.valButton)
        setUpRecyclerView()
    }

    private fun updateItems() {
        pesR.text = "hola"
    }

    fun validate (view: View?) {

        val newFragment = OrdersDialog(R.string.message,R.string.accept,R.string.cancel, ::updateItems)
        newFragment.show(supportFragmentManager, "hola")

        if (pesR.text.toString().toInt() >  weightRange.first && pesR.text.toString().toInt() >  weightRange.second) {
            Log.e("aa", "CORRECTE")
        }else{
            Log.e("aa", "INCORRECTE")
        }



    }
    fun setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.recylerview)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        //Agafem l'usuari
        val user = Repository.getInstance().getUser("DemoUser").get()!!

        //Calculem el weight
        val weightT = WeightCalculator.weightOfUserOrders(user)
        weightRange = weightT
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



