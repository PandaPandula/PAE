package com.sagalogistics.backend.api.models

import java.io.Serializable

/**
 * An item
 */
interface Item : Serializable {
    /**
     * The key of the item in the database
     */
    var key: String?

    /**
     * The name of the item
     */
    var name: String

    /**
     * The weight of the item
     */
    var weight: Float

    /**
     * The upper variance percentage in weight of the item
     *
     * Must be between 0 and 100
     */
    var upperVariance: Float

    /**
     * The lower variance percentage in weight of the item
     *
     * Must be between 0 and 100
     */
    var lowerVariance: Float

    /**
     * The url of an image representing the item
     */
    var image: String?
}