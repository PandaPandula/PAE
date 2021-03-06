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
import android.text.Layout
import android.view.View.*
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.material.snackbar.Snackbar
import com.sagalogistics.utils.dialogs.ItemDialog

class BarOrderActivity : AppCompatActivity()
{
    lateinit var mRecyclerView : RecyclerView
    private lateinit var pesT : TextView
    private lateinit var pesR : TextView
    private lateinit var result : TextView
    private lateinit var sadfas : View
    private lateinit var valButton : Button
    private lateinit var weightRange : Pair<Float, Float>

    private var barsList: List<Bar> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_order2)
        pesR = findViewById(R.id.PesR)
        pesT = findViewById(R.id.PesT)
        result = findViewById(R.id.result)
        sadfas = findViewById(R.id.introductionCreateTags)
        valButton = findViewById(R.id.valButton)
        setUpRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }

    private fun updateItems() {

        if (pesR.text.toString().toFloat() >=  weightRange.first && pesR.text.toString().toFloat() <= weightRange.second) {
            val snack = Snackbar.make(findViewById(R.id.bottom2),"EL PESATGE ??S CORRECTE",Snackbar.LENGTH_LONG)
            snack.show()
            mRecyclerView.visibility = INVISIBLE
            sadfas.visibility = VISIBLE
            valButton.text = "NOVA RUTA"


        }else{
            val snack = Snackbar.make(findViewById(R.id.bottom2),"EL PESATGE ??S INCORRECTE",Snackbar.LENGTH_LONG)
            snack.show()
        }
    }

    fun pes (view: View?) {
        val randomPes = (-30..30).random() + Integer.parseInt(pesT.text.toString())

        pesR.text = randomPes.toString()

    }

    fun validate (view: View?) {

        val newFragment = OrdersDialog(R.string.OrdersDialog,R.string.accept,R.string.cancel, ::updateItems)
        newFragment.show(supportFragmentManager, "hola")

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
        val medianweight = ((weightT.first + weightT.second) / 2)
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



