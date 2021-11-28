package com.sagalogistics.backend.models

import java.io.Serializable

interface User : Serializable{
    var key: String?
    var name: String
    var bars: MutableList<String>

    fun addBar(barKey: String)
    fun removeBar(barKey: String)
}