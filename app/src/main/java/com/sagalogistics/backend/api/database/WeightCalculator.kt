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
         * after considering its [upper][Item.upperVariance] and [lower][Item.lowerVariance] bounds
         *
         * The [first][Pair.first] element of the pair will be the upper bound of weight,
         * and the [second][Pair.second] will be the lower one
         */
        fun weightOfOrder(order: Order): Pair<Float, Float> {
            val itemKeys = order.items.keys
            val listOfItems = FutureHelper.getListOfKeys(itemKeys, Item::class)

            var upperWeight = 0f
            var lowerWeight = 0f
            for(item in listOfItems) {
                val weightOfItem = item.weight
                val upperVarianceOfItem = item.upperVariance
                val lowerVarianceOfItem = item.lowerVariance
                val quantityOfItem = order.items[item.key]!!

                val upperVariance = (weightOfItem + upperVarianceOfItem) * quantityOfItem
                val lowerVariance = (weightOfItem - lowerVarianceOfItem) * quantityOfItem

                upperWeight += upperVariance
                lowerWeight += lowerVariance
            }

            return Pair(upperWeight, lowerWeight)
        }

        /**
         * Calculates the weight of an [item][Item] in the [order]
         *
         * Similar to [weightOfOrder] but for a single [item][Item]
         */
        fun weightOfItemInOrder(order: Order, itemKey: String): Pair<Float, Float> {
            val repo = Repository.getInstance()
            val item = repo.getItem(itemKey).get()!!

            val weightOfItem = item.weight
            val upperVarianceOfItem = item.upperVariance
            val lowerVarianceOfItem = item.lowerVariance
            val quantityOfItem = order.items[item.key]!!

            val upperVariance = (weightOfItem + upperVarianceOfItem) * quantityOfItem
            val lowerVariance = (weightOfItem - lowerVarianceOfItem) * quantityOfItem

            return Pair(upperVariance, lowerVariance)
        }
    }
}