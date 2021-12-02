package com.sagalogistics.backend.api.database

import com.sagalogistics.backend.api.models.Item
import com.sagalogistics.backend.api.models.Order

/**
 * Utility class used to calculate the weight of an order
 *
 * These functionalities are separated from [Order] in order to prevent coupling it with the [Repository]
 *
 * @author Gerard Queralt
 * @constructor private constructor to prevent instantiation
 */
class WeightCalculator private constructor(){
    /**
     * Class methods
     */
    companion object{
        /**
         * Calculates the total weight of an [order]
         *
         * This function will call [Repository.getItem] to get the [weight][Item.weight] of each [item][Item]
         * and multiply it by its amount in the [order]
         */
        fun weightOfOrder(order: Order): Float { //maybe consider variation here?
            val itemKeys = order.items.keys
            val listOfItems = FutureHelper.getListOfKeys(itemKeys, Item::class)

            var totalWeight = 0f
            for(item in listOfItems) {
                val weightOfItem = item.weight
                val quantityOfItem = order.items[item.key]!!
                totalWeight += weightOfItem * quantityOfItem
            }

            return totalWeight
        }

        /**
         * Calculates the weight of an [item][Item] in the [order]
         *
         * Similar to [weightOfOrder] but for a single [item][Item]
         */
        fun weightOfItemInOrder(order: Order, itemKey: String): Float {
            val repo = Repository.getInstance()
            val item = repo.getItem(itemKey).get()!!

            val weightOfItem = item.weight
            val quantityOfItem = order.items[item.key]!!

            return weightOfItem * quantityOfItem
        }
    }
}