package com.sagalogistics.backend.api.models

import java.io.Serializable

interface Item : Serializable {
    var key: String?
    var name: String
    var weight: Float
    var image: String?
}