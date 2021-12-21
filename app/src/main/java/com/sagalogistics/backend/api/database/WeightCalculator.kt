package com.sagalogistics.backend.api.database

import com.sagalogistics.backend.api.models.Bar
import com.sagalogistics.backend.api.models.Item
import com.sagalogistics.backend.api.models.Order
import com.sagalogistics.backend.api.models.User

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
        fun weightOfUserOrders(user: User): Pair<Float, Float> {
            val barKeys = user.bars
            val listOfBars = FutureHelper.getListOfKeys(barKeys, Bar::class)

            return listOfBars.fold(Pair(0f, 0f), {
                total, bar ->

                val orderKeys = bar.orders
                val listOfOrders = FutureHelper.getListOfKeys(orderKeys, Order::class)

                val (lowerWeightBar, upperWeightBar) = listOfOrders.fold(Pair(0f, 0f), {
                    totalOrder, order ->

                    val (lowerWeightOrd, upperWeightOrd) = weightOfOrder(order)
                    Pair(totalOrder.first + lowerWeightOrd, totalOrder.second + upperWeightOrd)
                })
                Pair(total.first + lowerWeightBar,total.second + upperWeightBar)
            })
        }

        /**
         * Calculates the total weight of an [order]
         *
         * This function will call [Repository.getItem] to get the [weight][Item.weight] of each [item][Item]
         * and multiply it by its amount in the [order]
         * after considering its [upper][Item.upperVariance] and [lower][Item.lowerVariance] bounds
         *
         * The [first][Pair.first] element of the pair will be the lower bound of weight,
         * and the [second][Pair.second] will be the upper one
         */
        fun weightOfOrder(order: Order): Pair<Float, Float> {
            val itemKeys = order.items.keys
            val listOfItems = FutureHelper.getListOfKeys(itemKeys, Item::class)

            return listOfItems.fold(Pair(0f, 0f), {
                (lowerWeight, upperWeight), item ->

                val (lowerVariance, upperVariance) = upperLowerWeightOfItem(order, item)

                Pair(lowerWeight + lowerVariance, upperWeight + upperVariance)
            })
        }

        /**
         * Calculates the weight of a single [item][Item] in the [order]
         *
         * Similar to [weightOfOrder] but for a single [item][Item]
         */
        fun weightOfItemInOrder(order: Order, itemKey: String): Pair<Float, Float> {
            val repo = Repository.getInstance()
            val item = repo.getItem(itemKey).get()!!

            return upperLowerWeightOfItem(order, item)
        }

        /**
         * Calculates the weight of a single [item][Item] instance in the [order]
         *
         * Auxiliary function to encapsulate calculation logic
         */
        private fun upperLowerWeightOfItem(order: Order, item: Item): Pair<Float, Float> {
            val weightOfItem = item.weight
            val lowerVarianceOfItem = item.lowerVariance
            val upperVarianceOfItem = item.upperVariance
            val quantityOfItem = order.items[item.key]!!

            val lowerVariance = weightOfItem * (1 - lowerVarianceOfItem) * quantityOfItem
            val upperVariance = weightOfItem * (1 + upperVarianceOfItem) * quantityOfItem

            return Pair(lowerVariance, upperVariance)
        }
    }
}