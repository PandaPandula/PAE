package com.sagalogistics.lib.database

import java.util.concurrent.Future
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions
import com.sagalogistics.lib.models.*

/**
 * Utility class used to simplify dealing with Futures
 * @author Gerard Queralt
 *
 * @constructor private constructor to prevent instantiation
 */
class FutureHelper private constructor(){
    /**
     * Class methods
     */
    companion object{
        /**
         * Returns a [set][Set] with the objects in the database whose keys are in the [list of keys][listOfKeys]
         *
         * Given a [list of keys][listOfKeys] and the expected [type] of the objects,
         * performs calls to the [Repository] and returns a [collection][Set] with the results
         *
         * Internally, it simply calls [consumeFutures] with the result of calling [createFutures]
         *
         * Note that [type] should be either [Item], [Order], [Bar] or [User]; otherwise an exception will be thrown
         *
         * @throws [NoSuchMethodException] if the [Repository] doesn't have a function to get objects of the given [type]
         */
        fun<T: Any> getListOfKeys(listOfKeys: Collection<String>, type: KClass<T>): Set<T> {
            val setOfFutures = createFutures(listOfKeys, type)
            return consumeFutures(setOfFutures)
        }

        /**
         * Calls [Repository].get&lt;[type]&gt;(key) on each key in the [list][listOfKeys]
         * and returns a [set][Set] with the resulting [futures][Future]
         *
         * Essentially, it behaves like a map function in other programming languages
         *
         * @throws [NoSuchMethodException] if the [Repository] doesn't have a function to get objects of the given [type]
         */
        fun<T: Any> createFutures(listOfKeys: Collection<String>, type: KClass<T>): Set<Future<T>> {
            val getterOfT = findGetterOfType(type)
            val repo = Repository.getInstance()

            val setOfFutures = LinkedHashSet<Future<T>>()
            for(k in listOfKeys){
                val future = getterOfT.call(repo, k)
                setOfFutures.add(future as Future<T>)
            }

            return setOfFutures
        }

        /**
         * Consumes all futures in a [list][listOfFutures] and returns a [set][Set] with its values
         *
         * Loops the [list][listOfFutures] checking if any [future][Future] is done, and if it is, it consumes it.
         * This way it should block the execution for as little time as possible
         */
        fun<T: Any> consumeFutures(listOfFutures: Collection<Future<T>>): Set<T> {
            val gettedValues = LinkedHashSet<T>()
            val mutableListOfFutures = LinkedHashSet(listOfFutures)

            while(mutableListOfFutures.isNotEmpty()){
                val itr = mutableListOfFutures.iterator()
                while (itr.hasNext()){
                    val f = itr.next()
                    if(f.isDone){
                        val value = f.get()
                        value?.let { gettedValues.add(value) }
                        itr.remove()
                    }
                }
            }

            return gettedValues
        }

        /**
         * Finds the function in [Repository] used to get objects of a given [type]
         *
         * Using reflection, this method searches a function called get&lt;[type].[simpleName][KClass.simpleName]&gt;
         *
         * For example, calling this method with [type] = Item::class will return the function [Repository.getItem]
         *
         * @throws [NoSuchMethodException] if the [Repository] doesn't have a function to get objects of the given [type]
         */
        private fun <T : Any> findGetterOfType(type: KClass<T>): KFunction<*> {
            val repoClass = Repository::class
            val funcsOfRepo = repoClass.declaredFunctions
            return funcsOfRepo.find { it.name == "get" + type.simpleName }
                ?: throw NoSuchMethodException("Repository can't get objects of type " + type.simpleName)
        }
    }
}