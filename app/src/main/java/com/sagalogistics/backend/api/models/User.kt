package com.sagalogistics.backend.api.models

import java.io.Serializable

interface User : Serializable{
    var key: String?
    var name: String
    var bars: MutableList<String>

    fun addBar(barKey: String)
    fun removeBar(barKey: String)
}