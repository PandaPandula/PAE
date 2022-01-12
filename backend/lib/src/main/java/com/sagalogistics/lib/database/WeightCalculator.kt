package com.sagalogistics.lib.database

import com.sagalogistics.lib.models.*

/**
 * Utility class used to calculate the weight of an order
 *
 * These functionalities are separated from [Order] and [User]
 * in order to prevent coupling it with the [Repository]
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
         * Calculates the total weight of all the [return][Order.isReturn] [orders][Order] of a [user]
         *
         * This function will call [Repository.getBar] to get the [bars][Bar]
         * assigned to the user, and call [weightOfBarOrders] on each of them
         *
         * The [first][Pair.first] element of the pair will be the lower bound of weight,
         * and the [second][Pair.second] will be the upper one
         */
        fun weightOfUserOrders(user: User): Pair<Float, Float> {
            val barKeys = user.bars
            val listOfBars = FutureHelper.getListOfKeys(barKeys, Bar::class)

            return listOfBars.fold(Pair(0f, 0f), { (lowerWeight, upperWeight), bar
                ->
                val (lowerWeightBar, upperWeightBar) = weightOfBarOrders(bar)
                Pair(lowerWeight + lowerWeightBar, upperWeight + upperWeightBar)
            })
        }

        /**
         * Calculates the total weight of all the [return][Order.isReturn] [orders][Order] of a [bar]
         *
         * This function will call [Repository.getOrder] to get the [orders][Order]
         * made by the bar, and call [weightOfOrder] on each of them
         *
         * The [first][Pair.first] element of the pair will be the lower bound of weight,
         * and the [second][Pair.second] will be the upper one
         */
        private fun weightOfBarOrders(bar: Bar): Pair<Float, Float> {
            val orderKeys = bar.orders
            val listOfOrders = FutureHelper.getListOfKeys(orderKeys, Order::class)

            return listOfOrders.fold(Pair(0f, 0f), { (lowerWeight, upperWeight), order
                ->
                val (lowerWeightOrd, upperWeightOrd) = weightOfOrder(order)
                Pair(lowerWeight + lowerWeightOrd, upperWeight + upperWeightOrd)
            })
        }

        /**
         * Calculates the total weight of a [return][Order.isReturn] [order]
         *
         * This function will call [Repository.getItem] to get the [weight][Item.weight]
         * of each [item][Item] and multiply it by its amount in the [order]
         * after considering its [upper][Item.upperVariance] and [lower][Item.lowerVariance] bounds
         *
         * The [first][Pair.first] element of the pair will be the lower bound of weight,
         * and the [second][Pair.second] will be the upper one
         *
         * If the order is [not a return order][Order.isReturn] this function returns
         * a [pair][Pair] of zeroes
         */
        fun weightOfOrder(order: Order): Pair<Float, Float> {
            if (order.isReturn) {
                val itemKeys = order.items.keys
                val listOfItems = FutureHelper.getListOfKeys(itemKeys, Item::class)

                return listOfItems.fold(Pair(0f, 0f), { (lowerWeight, upperWeight), item ->
                    val (lowerVariance, upperVariance) = lowerUpperWeightOfItem(order, item)
                    Pair(lowerWeight + lowerVariance, upperWeight + upperVariance)
                })
            }
            return Pair(0f, 0f)
        }

        /**
         * Calculates the weight of a single [item][Item] in the [order]
         *
         * Similar to [weightOfOrder] but for a single [item][Item]
         */
        fun weightOfItemInOrder(order: Order, itemKey: String): Pair<Float, Float> {
            val repo = Repository.getInstance()
            val item = repo.getItem(itemKey).get()!!

            return lowerUpperWeightOfItem(order, item)
        }

        /**
         * Calculates the weight of a single [item][Item] instance in the [order]
         *
         * Auxiliary function to encapsulate calculation logic
         */
        private fun lowerUpperWeightOfItem(order: Order, item: Item): Pair<Float, Float> {
            val weightOfItem = item.weight
            val lowerVarianceOfItem = item.lowerVariance / 100f
            val upperVarianceOfItem = item.upperVariance / 100f
            val quantityOfItem = order.items[item.key]!!

            val lowerVariance = weightOfItem * (1 - lowerVarianceOfItem) * quantityOfItem
            val upperVariance = weightOfItem * (1 + upperVarianceOfItem) * quantityOfItem

            return Pair(lowerVariance, upperVariance)
        }
    }
}