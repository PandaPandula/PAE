package com.sagalogistics.loginactivity

import android.app.Activity
import android.os.Bundle
import com.sagalogistics.R

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sagalogistics.BarOrderActivity
import com.sagalogistics.lib.database.Repository
import com.sagalogistics.implementation.database.RepositoryFactoryFirebase


class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Repository.initialize(RepositoryFactoryFirebase())
    }

    fun checkAndLogin(View: View?){
        startActivity(Intent(this,BarOrderActivity::class.java))
    }
}
