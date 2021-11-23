package com.example.myapplication

import com.sagalogistics.backend.models.Item
import com.sagalogistics.backend.models.ItemImpl
import com.sagalogistics.backend.models.OrderImpl
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_order(){
        var test = OrderImpl()
        test.addItem("hola", 0)
        var test2 = test.order
        test2.put("hola", 1)
        assertEquals(0, test.getQuantityItem("hola"))
    }

    @Test
    fun test_shallow_copy(){
        var test = HashMap<String, Item>()
        test.put("a", ItemImpl("test", 0.0f))
        var test2 = HashMap<String, Item>(test)
        var item = test2.get("a")
        if (item != null) {
            item.key = "test2"
        }
        assertEquals("test", test.get("a")?.key)
    }
}