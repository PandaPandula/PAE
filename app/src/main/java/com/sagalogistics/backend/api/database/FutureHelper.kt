package com.sagalogistics.backend.api.database

import java.util.concurrent.Future
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions

/**
 * Utility class used that simplifies dealing with Futures
 */
/**
 * @constructor private constructor so it can't be instantiated
 */
class FutureHelper private constructor(){
    companion object{
        fun<T: Any> getListOfKeys(listOfKeys: Collection<String>, type: KClass<T>): Set<T> {
            val setOfFutures = createFutures(listOfKeys, type)

            return consumeFutures(setOfFutures)
        }

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

        private fun <T : Any> findGetterOfType(type: KClass<T>): KFunction<*> {
            val repoClass = Repository::class
            val funcsOfRepo = repoClass.declaredFunctions
            //getterOfT is the function in the Repository that gets the type T from the Database
            return funcsOfRepo.find { it.name == "get" + type.simpleName }
                ?: throw NoSuchMethodException("Repository can't get objects of type " + type.simpleName)
        }
    }
}