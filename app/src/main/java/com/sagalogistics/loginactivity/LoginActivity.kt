package com.sagalogistics.loginactivity

import android.app.Activity
import android.os.Bundle
import com.sagalogistics.R
import com.sagalogistics.registeractivity.RegisterActivity

import android.content.Intent
import android.view.View
import com.sagalogistics.BarOrderActivity


class LoginActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onLoginClick(View: View?) {
        startActivity(Intent(this, RegisterActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun checkAndLogin(View: View?){
        startActivity(Intent(this,BarOrderActivity::class.java))
    }
}
