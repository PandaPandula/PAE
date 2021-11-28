package com.sagalogistics.registeractivity

import android.app.Activity
import android.os.Bundle

import com.sagalogistics.loginactivity.LoginActivity

import android.content.Intent

import android.view.View
import com.sagalogistics.R


class RegisterActivity: Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }


    fun onLoginClick(view: View?) {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}