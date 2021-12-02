package com.sagalogistics.backend.api.models

import com.sagalogistics.backend.api.database.FutureHelper
import com.sagalogistics.backend.api.database.Repository

class WeightCalculator private constructor(){
    companion object{
        fun weightOfOrder(order: Order): Float {
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

        fun weightOfItemInOrder(order: Order, itemKey: String): Float {
            val repo = Repository.getInstance()
            val item = repo.getItem(itemKey).get()!!

            val weightOfItem = item.weight
            val quantityOfItem = order.items[item.key]!!

            return weightOfItem * quantityOfItem
        }
    }
}